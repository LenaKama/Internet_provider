package by.kamotskaya.epam.repository;

import by.kamotskaya.epam.entity.Entity;
import by.kamotskaya.epam.specification.BaseSpecification;

/**
 * @author Lena Kamotskaya
 */
public interface BaseRepository {

    void add(Entity entity);
    void delete(Entity entity);
    void update(Entity entity);
    void query(BaseSpecification specification);

}
