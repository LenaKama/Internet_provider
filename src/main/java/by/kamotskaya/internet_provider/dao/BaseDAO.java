package by.kamotskaya.internet_provider.dao;

import by.kamotskaya.internet_provider.entity.Entity;
import by.kamotskaya.internet_provider.exception.DAOException;

/**
 * @author Lena Kamotskaya
 */
public interface BaseDAO<T extends Entity> {

    void add(T entity) throws DAOException;
    void delete(String us_login) throws DAOException;
    void update(T entity) throws DAOException;
}
