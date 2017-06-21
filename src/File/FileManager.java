package File;

import Structure.Config;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import model.Naoty;
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

    public static void openFile(Naoty n) {
        try {
            Path file = Paths.get(Config.pathNoteFile + n.getId() + ".txt");
            Runtime r = Runtime.getRuntime();
            r.exec(Config.defaultEditor+" "+file);
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void renameFile(Naoty naoty) {
    }

    public static void parseFile() {

    }
}
