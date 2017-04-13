package com.criticalgnome.blog.dao;

import com.criticalgnome.blog.entities.AbstractEntity;
import com.criticalgnome.blog.exceptions.DaoException;

import java.util.List;

/**
 * Project Blog
 * Created on 20.03.2017.
 *
 * @author CriticalGnome
 */
public interface IDao<T extends AbstractEntity> {
    /**
     * Create new row in table
     * @param abstractEntity object
     * @return id for created row
     * @throws DaoException custom exception
     */
    Long create(T abstractEntity) throws DaoException;

    /**
     * Get one row from table by id
     * @param id row id
     * @return row object
     * @throws DaoException custom exception
     */
    T getById(Long id) throws DaoException;

    /**
     * Update object data in table
     * @param abstractEntity object
     * @throws DaoException custom exception
     */
    void update(T abstractEntity) throws DaoException;

    /**
     * Remove row from table by id
     * @param id id
     * @throws DaoException custom exception
     */
    void remove(Long id) throws DaoException;

    /**
     * Get list of all records
     * @return list
     * @throws DaoException custom exception
     */
    public List<T> getAll() throws DaoException;

}
