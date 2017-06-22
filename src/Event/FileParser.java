package Event;

import Database.KeywordDAO;
import Structure.Config;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Keyword;

/**
 *
 * @author Andry
 */
public class FileParser extends Thread implements ProcessListened {

    private ArrayList<EditorFileProcess> processDataList = new ArrayList<EditorFileProcess>();
    private Thread t;
    private KeywordDAO keywordDAO;

    public FileParser(KeywordDAO keywordDAO) {
        this.keywordDAO = keywordDAO;
        t = new Thread(this);
        t.start();
    }

    public void run() {
        while (true) {
            try {
                for (EditorFileProcess processData : processDataList) {
                    if (!processData.getProcess().isAlive()) {
                        parseFile(processData.getId());
                        processDataList.remove(processData);
                    }
                }
            } catch (ConcurrentModificationException e){
                System.out.println("Error: "+ e.toString());
            }
        }
    }

    @Override
    public void addProcessData(EditorFileProcess editorFileProcess) {
        processDataList.add(editorFileProcess);
    }

    @Override
    public void deleteProcessListener() {
        processDataList.clear();
    }

    @Override
    public void updateProcessListener() {

    }

    public void parseFile(int idFile) {
        try {
            Scanner scanner = new Scanner(new File(Config.pathNoteFile+idFile+".txt"));
            while (scanner.hasNext()) {
                String key = scanner.nextLine();
                if (key.startsWith("#")) {
                    key = key.replace("#", "");
                    key = key.replace(":", "");
                    keywordDAO.insert(new Keyword(key, idFile));
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TestScannerFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
