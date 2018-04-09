package by.kamotskaya.epam.dao;

import by.kamotskaya.epam.entity.Entity;
import by.kamotskaya.epam.exception.DAOException;

import java.util.List;

/**
 * @author Lena Kamotskaya
 */
public interface BaseDAO<T> {

    List<Object> add(T entity) throws DAOException;
    void delete(Entity entity);
    void update(String us_login) throws DAOException;

}
