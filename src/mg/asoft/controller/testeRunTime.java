
package mg.asoft.controller;

import mg.asoft.structure.Config;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andry
 */
public class testeRunTime {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Runtime r = Runtime.getRuntime();
            r.exec(Config.defaultEditor);
        } catch (IOException ex) {
            Logger.getLogger(testeRunTime.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
