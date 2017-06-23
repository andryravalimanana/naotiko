package naotiko;

import Controller.MainViewController;
import Database.Database;
import Database.KeywordDAO;
import Database.NaotyDAO;
import Structure.Config;
import View.MainView;
import java.awt.GraphicsDevice;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;

/**
 *
 * @author Andry
 */
public class main {

    public static void main(String[] args) {
        try {
            try {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (Exception e) {
                System.err.println("Erreur:" + e);
            }
            Config.loadConfig();
            NaotyDAO naotyDAO = new NaotyDAO(Database.getInstance());
            KeywordDAO keywordDAO = new KeywordDAO(Database.getInstance());
//        
//        JFrame mainView = new JFrame();
//        MainPanel mainPanel = new MainPanel();
//        mainView.add(mainPanel);
//        mainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        mainView.setLocationRelativeTo(null);
//        mainView.setVisible(true);
//        mainView.pack();
//        
//        MainViewController mvc = new MainViewController(mainPanel, naotyDAO, keywordDAO);
            MainView mv = new MainView(keywordDAO);
            MainViewController mvc = new MainViewController(mv, naotyDAO, keywordDAO);
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
