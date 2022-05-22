package pao.library.api.dao;

import pao.library.api.JdbcConnection;

import java.sql.Connection;
import java.util.Collection;

/**
 * Interface for the Data Access Object (DAO) pattern.
 *
 * @param <T> - entity type
 * @param <K> - primary key type
 */
public abstract class Dao<T, K> {
    protected Connection connection;

    public Dao() {
        connection = JdbcConnection.getConnection();
    }

    public abstract T get(K id);

    public abstract Collection<T> getAll();

    public abstract K save(T t);

    public abstract void update(T t);

    public abstract void delete(T t);
}
