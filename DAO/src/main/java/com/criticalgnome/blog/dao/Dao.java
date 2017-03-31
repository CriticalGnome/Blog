package com.criticalgnome.blog.dao;

import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.entities.Pojo;

/**
 * Project Blog
 * Created on 20.03.2017.
 *
 * @author CriticalGnome
 */
public interface Dao<T extends Pojo> {
    Long create(T pojo) throws DaoException;
    T getById(Long id) throws DaoException;
    void update(T pojo) throws DaoException;
    void remove(Long id) throws DaoException;
}
