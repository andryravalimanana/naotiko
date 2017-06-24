package mg.asoft.database;

import java.util.ArrayList;
import mg.asoft.model.Keyword;
import mg.asoft.model.Naoty;

/**
 *
 * @author Andry
 */
public class testDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*  try {
         //        try {
         NaotyDAO naotyDAO = new NaotyDAO(Database.getInstance());
         //            Naoty n = naotyDAO.findById(6);
         //            System.out.println("Id: "+ n.getId()+" Date: "+n.getDate()+ " Time: "+n.getTime()+ " Title: "+ n.getTitle());
         //            Database.getInstance().close();
         //        } catch (SQLException ex) {
         //            Logger.getLogger(testDB.class.getName()).log(Level.SEVERE, null, ex);
         //        }
         //  KeywordDAO keywordDAO = new KeywordDAO(Database.getInstance());
         //        Keyword k = new Keyword(5, "ETO AHOA", 3);
         //        keywordDAO.update(k);

         //            ArrayList<Keyword> k = keywordDAO.findByKeyword("iS");
         //            for (Keyword k1 : k) {
         //                System.out.println("ID: "+k1.getId()+ " TITLE: "+k1.getTitle() +" ID NOTE: "+k1.getIdNaoty());
         //            }
         //  System.out.println("Id: "+ k.getId()+" Title: "+k.getTitle()+ " ID NOTE: "+k.getIdNaoty());
         //            ArrayList<Keyword> k = keywordDAO.findByKeyword("iS");
         //            ArrayList<Keyword> k = keywordDAO.findByKeyword("iS");
         ArrayList<Naoty> naotys = naotyDAO.findByKeyword("hAT");
         for (Naoty naoty : naotys) {
         System.out.println("ID: " + naoty.getId()+ " DATE: " + naoty.getDate()+ " TIME: " + naoty.getTime() + " TITLE: " + naoty.getTitle());
         }
         Database.getInstance().close();
         } catch (SQLException ex) {
         Logger.getLogger(testDB.class.getName()).log(Level.SEVERE, null, ex);
         }
         */
        //System.out.println(KeywordDAO.firstKeywordOf(3));
//        Keyword k = new Keyword("JVM", 35);
        KeywordDAO kdao = new KeywordDAO(Database.getInstance());
//        kdao.insert(k);
        NaotyDAO naotyDAO = new NaotyDAO(Database.getInstance());
        ArrayList<Naoty> ndaos = naotyDAO.findByKeyword("");
        for (Naoty ndao : ndaos) {
            ndao.setFirstKeyword(ndao.getTitle());
            kdao.insert(new Keyword(ndao.getTitle(), ndao.getId()));
            System.out.println(ndao.getId() + ": " + ndao.getTitle() + " Done ...");
        }
    }
}
