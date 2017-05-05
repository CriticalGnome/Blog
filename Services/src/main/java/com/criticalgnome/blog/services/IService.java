package com.criticalgnome.blog.services;

import com.criticalgnome.blog.entities.AbstractEntity;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.exceptions.ServiceException;

import java.util.List;

/**
 * Project Blog
 * Created on 22.03.2017.
 *
 * @author CriticalGnome
 */
public interface IService<T extends AbstractEntity> {

    /**
     * Create new entity instance
     *
     * @param abstractEntity generic entity
     * @return id
     * @throws ServiceException custom exception
     */
    Long create(T abstractEntity) throws ServiceException;

    /**
     * Return generic instance by id
     * @param id id
     * @return entity
     * @throws ServiceException custom exception
     */
    T getById(Long id) throws ServiceException;

    /**
     * Update generic entity
     *
     * @param abstractEntity entity instance
     * @throws ServiceException custom exception
     */
    void update(T abstractEntity) throws ServiceException;

    /**
     * Delete entity instance
     *
     * @param id id
     * @throws ServiceException custom exception
     */
    void remove(Long id) throws ServiceException;

    /**
     * List of all instances of generic entity
     *
     * @return List
     * @throws ServiceException custom exception
     */
    List<T> getAll() throws ServiceException;

}
