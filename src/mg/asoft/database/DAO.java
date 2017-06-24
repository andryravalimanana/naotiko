package mg.asoft.database;

import java.sql.Connection;
import java.util.ArrayList;
/**
 *
 * @author Andry
 */
public abstract class DAO<T> {
    protected Connection connection;

    public DAO(Connection connection) {
        this.connection = connection;
    }
    public abstract boolean insert(T object);
    public abstract boolean delete(T object);
    public abstract boolean update(T object);
    public abstract T findById(int id);
    public abstract ArrayList<T> findByKeyword(String keyword);
}
