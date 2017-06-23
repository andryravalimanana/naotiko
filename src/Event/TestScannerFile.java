package Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andry
 */
public class TestScannerFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        try {
//            Scanner scanner = new Scanner(new File("G:\\10.txt"));
//            while (scanner.hasNext()) {
//                String key = scanner.nextLine();
//                if (key.startsWith("#")) {
//                    key = key.replace("#", "");
//                    key = key.replace(":", "");
//                    System.out.println(key);
//                }
//            }
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(TestScannerFile.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
        String a = "DAO";
        String b = "PIX";
        if(!a.equals(b)){
            System.out.println("a et b different");
        }
   }

}
