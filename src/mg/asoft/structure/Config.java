package mg.asoft.structure;

import java.io.File;
import java.io.IOException;
import org.ini4j.Wini;

/**
 *
 * @author Andry
 */
public class Config {
    public static String defaultEditor = "C:\\Program Files\\Sublime Text 3\\subl.exe";
    public static String pathNoteFile = "./Naoty/";
    public static String pathDatabase = "./Database/";
    public static String pathConfigFile = Config.class.getResource("/mg/asoft/structure/").getPath();
    
    public static Boolean loadConfig() throws IOException{
        Wini iniFile = new Wini(new File(pathConfigFile+"configuration.ini"));
        defaultEditor = iniFile.get("Editor", "path");
        pathNoteFile = iniFile.get("Naoty", "path");
        pathDatabase =  iniFile.get("Database", "path");
        return true;
    }
    
    public static Boolean updateConfig() throws IOException{
        Wini iniFile = new Wini(new File(pathConfigFile+"configuration.ini"));
        iniFile.put("Editor","path", defaultEditor);
        iniFile.put("Naoty", "path", pathNoteFile);
        iniFile.store();
        return true;
    }
}
