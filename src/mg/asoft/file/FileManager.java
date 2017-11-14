package mg.asoft.file;

import mg.asoft.event.EditorFileProcess;
import mg.asoft.event.FileParser;
import mg.asoft.structure.Config;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import mg.asoft.model.Naoty;
import java.nio.file.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andry
 */
public class FileManager {

    private FileManager() {
    }

    public static void createFile(Naoty naoty) {
        Path file = Paths.get(Config.pathNoteFile + naoty.getId() + ".txt");
        String s = "*******************************************************************************************************************************************************\n"
                + "TITRE: " + naoty.getTitle() + "\nID: " + naoty.getId() + "\nDate: " + naoty.getDate().toString() + "\nHEURE: " + naoty.getTime().toString()
                + "\n*******************************************************************************************************************************************************\n\n";
        byte data[] = s.getBytes();
        try (OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(file, StandardOpenOption.CREATE, StandardOpenOption.APPEND))) {
            out.write(data, 0, data.length);
            out.close();
        } catch (IOException x) {
            System.err.println(x);
        }
    }

    public static void openFile(Naoty n, FileParser fileParser) {
        try {
            Path file = Paths.get(Config.pathNoteFile + n.getId() + ".txt");
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(Config.defaultEditor + " -w " + file);
            fileParser.addProcessData(new EditorFileProcess(n.getId(), p));
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void renameFile(Naoty naoty) {

    }
}
