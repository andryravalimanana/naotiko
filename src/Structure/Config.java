package Structure;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ini4j.Wini;

/**
 *
 * @author Andry
 */
public class Config {
    public static String defaultEditor = "C:\\Program Files\\Sublime Text 3\\subl.exe";
    public static String pathNoteFile = "../Naoty/";
    public static String pathDatabase = "../Database/";
    public static String pathConfigFile = "../Config/";
    public static Boolean loadConfig(){
        try {
            Wini iniFile = new Wini(new File(pathConfigFile+"configuration.ini"));
            defaultEditor = iniFile.get("Editor", "path");
            pathNoteFile = iniFile.get("Naoty", "path");
            pathDatabase =  iniFile.get("Database", "path");
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
