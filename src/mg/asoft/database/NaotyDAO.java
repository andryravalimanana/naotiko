package mg.asoft.database;

import mg.asoft.dateAndTime.Date;
import mg.asoft.dateAndTime.Time;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mg.asoft.model.Naoty;

/**
 *
 * @author Andry
 */
public class NaotyDAO extends DAO<Naoty> {

    public NaotyDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean insert(Naoty naoty) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO NAOTY(DATE, TIME, TITLE, KEYWORD) VALUES (\""
                    + naoty.getDate().toString() + "\",\""
                    + naoty.getTime().toString() + "\",\""
                    + naoty.getTitle() + "\",\""
                    + naoty.getFirstKeyword() + "\");");
            connection.commit();
            statement.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(NaotyDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean delete(Naoty naoty) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM NAOTY WHERE ID=" + naoty.getId() + ";");
            connection.commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(NaotyDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean update(Naoty naoty) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE NAOTY SET "
                    + "DATE = \"" + naoty.getDate()
                    + "\", TIME = \"" + naoty.getTime()
                    + "\", TITLE = \"" + naoty.getTitle()
                    + "\", KEYWORD = \"" + naoty.getFirstKeyword()
                    + "\"WHERE ID = " + naoty.getId() + ";");
            connection.commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(NaotyDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Naoty findById(int id) {
        Naoty naoty = null;
        try {
            ResultSet result = this.connection.createStatement().executeQuery("SELECT * FROM NAOTY WHERE ID = " + id + ";"
            );
            while (result.next()) {
                naoty = new Naoty(id, new Date(result.getString("DATE")), new Time(result.getString("TIME")), result.getString("TITLE"), KeywordDAO.firstKeywordOf(id));
            }
            result.close();
        } catch (SQLException ex) {
            Logger.getLogger(NaotyDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return naoty;
    }

    @Override
    public ArrayList<Naoty> findByKeyword(String keyword) {
        ArrayList<Naoty> listNaoty = new ArrayList<Naoty>();
        try {
            ResultSet result = this.connection.createStatement().executeQuery("SELECT * FROM NAOTY WHERE TITLE LIKE \"%" + keyword + "%\""
            );
            while (result.next()) {
                listNaoty.add(new Naoty(result.getInt("ID"), new Date(result.getString("DATE")), new Time(result.getString("TIME")), result.getString("TITLE"), KeywordDAO.firstKeywordOf(result.getInt("ID"))));
            }
            Database.getInstance().commit();
        } catch (SQLException ex) {
            Logger.getLogger(NaotyDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listNaoty;
    }

    public int nextNoteId() {
        int nextID = 0;
        try {
            ResultSet result = this.connection.createStatement().executeQuery("SELECT * FROM NAOTY;");
            while (result.next()) {
                nextID = result.getInt("ID");
            }
            nextID++;
            result.close();
        } catch (SQLException ex) {
            Logger.getLogger(NaotyDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nextID;
    }
}
