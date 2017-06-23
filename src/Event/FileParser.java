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
            } catch (ConcurrentModificationException e) {
                System.out.println("Error: " + e.toString());
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
            Scanner scanner = new Scanner(new File(Config.pathNoteFile + idFile + ".txt"));
            ArrayList<Keyword> keyList = keywordDAO.findByNoteID(idFile);
            ArrayList<String> listString = new ArrayList<String>();
            System.out.println("****************************************");
            for (Keyword keyList1 : keyList) {
                System.out.println(keyList1.getId() + ": " + keyList1.getTitle());
                listString.add(keyList1.getTitle());
            }
            System.out.println("*****************************************");
            //keywordDAO.insert(new Keyword("ANDRANA", idFile));
            while (scanner.hasNext()) {
                String key = scanner.nextLine();
                if (key.startsWith("#")) {
                    key = key.replace("#", "");
                    key = key.replace(":", "");
                    key = key.replace("'", "");
                    if(!listString.contains(key)){
                        keywordDAO.insert(new Keyword(key, idFile));
                    } else {
                        System.out.println("Efa Misy: "+key);
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TestScannerFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
