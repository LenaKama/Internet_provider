package by.kamotskaya.epam.dao;

import by.kamotskaya.epam.entity.Entity;
import by.kamotskaya.epam.exception.DAOException;

import java.util.List;

/**
 * @author Lena Kamotskaya
 */
public interface BaseDAO<T extends Entity> {

    List<Object> add(T entity) throws DAOException;
    void delete(String us_login) throws DAOException;
    void update(String us_login) throws DAOException;

}
