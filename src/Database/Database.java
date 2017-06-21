package Database;

import Structure.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andry
 */
public class Database {

    private static Connection connection;

    private Database() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:"+Config.pathDatabase+"Naoty.db");
            connection.setAutoCommit(false);
            System.out.println("Connected to the database ...");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Connection getInstance() {
        if (connection == null) {
            new Database();
        }
        return connection;
    }
}
