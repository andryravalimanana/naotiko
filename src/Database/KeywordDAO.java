package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Keyword;

/**
 *
 * @author Andry
 */
public class KeywordDAO extends DAO<Keyword> {

    public KeywordDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean insert(Keyword keyword) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO KEYWORD(TITLE, ID_NOTE) VALUES ('"
                    + keyword.getTitle() + "' ,'"
                    + keyword.getIdNaoty() + "');");
            Database.getInstance().commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(KeywordDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean delete(Keyword keyword) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM KEYWORD WHERE ID=" + keyword.getId() + ";");
            Database.getInstance().commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(KeywordDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean update(Keyword keyword) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE KEYWORD SET "
                    + "TITLE = '" + keyword.getTitle()
                    + "', ID_NOTE = '" + keyword.getIdNaoty()
                    + "' WHERE ID = " + keyword.getId() + ";");
            Database.getInstance().commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(NaotyDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Keyword findById(int id) {
        Keyword keyword = new Keyword();
        try {
            ResultSet result = this.connection.createStatement().executeQuery("SELECT * FROM KEYWORD WHERE ID = " + id + ";"
            );
            while (result.next()) {
                keyword = new Keyword(id, result.getString("TITLE"), result.getInt("ID_NOTE"));
            }
            Database.getInstance().commit();
            result.close();
        } catch (SQLException ex) {
            Logger.getLogger(NaotyDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return keyword;
    }

    @Override
    public ArrayList<Keyword> findByKeyword(String keyword) {
        ArrayList<Keyword> listKeywords = new ArrayList<Keyword>();
        try {
            ResultSet result = this.connection.createStatement().executeQuery("SELECT * FROM KEYWORD WHERE TITLE LIKE '%" + keyword + "%';"
            );
            while (result.next()) {
                listKeywords.add(new Keyword(result.getInt("ID"), result.getString("TITLE"), result.getInt("ID_NOTE")));
            }
            result.close();
        } catch (SQLException ex) {
            Logger.getLogger(KeywordDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listKeywords;
    }

    public ArrayList<Keyword> findByNoteID(int idNote) {
        ArrayList<Keyword> listKeywords = new ArrayList<Keyword>();
        try {
            ResultSet result = this.connection.createStatement().executeQuery("SELECT * FROM KEYWORD WHERE ID_NOTE = " + idNote + ";"
            );
            while (result.next()) {
                listKeywords.add(new Keyword(result.getInt("ID"), result.getString("TITLE"), result.getInt("ID_NOTE")));
            }
            result.close();
        } catch (SQLException ex) {
            Logger.getLogger(KeywordDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listKeywords;
    }

    public static String firstKeywordOf(int idNote) {
        String keyword = "";
        try {
            ResultSet result = Database.getInstance().createStatement().executeQuery("SELECT * FROM KEYWORD WHERE ID_NOTE = " + idNote + ";"
            );
            while (result.next()) {                
                keyword = result.getString("TITLE");
                break;
            }
            result.close();
        } catch (SQLException ex) {
            Logger.getLogger(KeywordDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return keyword;
    }
}
