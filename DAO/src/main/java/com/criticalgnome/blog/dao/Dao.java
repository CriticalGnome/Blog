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
    /**
     * Create new row in table
     * @param pojo object
     * @return id for created row
     * @throws DaoException custom exception
     */
    Long create(T pojo) throws DaoException;

    /**
     * Get one row from table by id
     * @param id row id
     * @return row object
     * @throws DaoException custom exception
     */
    T getById(Long id) throws DaoException;

    /**
     * Update object data in table
     * @param pojo object
     * @throws DaoException custom exception
     */
    void update(T pojo) throws DaoException;

    /**
     * Remove row from table by id
     * @param id id
     * @throws DaoException custom exception
     */
    void remove(Long id) throws DaoException;
}
