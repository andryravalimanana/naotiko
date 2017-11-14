package mg.asoft.structure;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Properties;
import mg.asoft.dateAndTime.Date;

/**
 *
 * @author Andry
 */
public class Config {

    public static String defaultEditor = "C:\\Program Files\\Sublime Text 3\\subl.exe";
    public static String pathNoteFile = System.getProperty("user.home") + "\\Documents\\";
    public static String pathDatabase = pathNoteFile;
    public static String pathConfigFile = "./Config/";
    public static ArrayList<Path> DBBackupList = new ArrayList<Path>();

    public static Boolean loadConfig() throws IOException {

        Properties properties = new Properties();
//============== Ici le fichier contenant les données de configuration est nommé 'db.properties' =============
        FileInputStream in = new FileInputStream("./Config/configuration.properties");
        properties.load(in);
// ================ Extraction des propriétés ======================
        defaultEditor = properties.getProperty("Editor.path");
        System.out.println("Editor: "+ defaultEditor);
        pathNoteFile = properties.getProperty("Naoty.path");
        pathDatabase = pathNoteFile;
        in.close();
        return true;
    }

    public static Boolean updateConfig() throws IOException {
        Properties properties = new Properties();
//============== Ici le fichier contenant les données de configuration est nommé 'db.properties' =============
        FileInputStream in = new FileInputStream("./Config/configuration.properties");
        properties.load(in);
// ================ Ajout des propriétés ======================
        properties.put("Editor.path", defaultEditor);
        properties.put("Naoty.path", pathNoteFile);
       // properties.put("Database", pathDatabase);
        FileOutputStream out = new FileOutputStream("./Config/configuration.properties");
        properties.store(out, "========== Insert data propertie ==========");
        in.close();
        return true;
    }
}
