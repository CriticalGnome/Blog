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

    Long create(T abstractEntity) throws ServiceException;
    T getById(Long id) throws ServiceException;
    void update(T abstractEntity) throws ServiceException;
    void remove(Long id) throws ServiceException;
    List<T> getAll() throws ServiceException;
}
