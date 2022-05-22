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

    abstract T get(K id);

    abstract Collection<T> getAll();

    abstract T save(T t);

    abstract void update(T t);

    abstract void delete(T t);
}
