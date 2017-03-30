package com.criticalgnome.blog.services;

import com.criticalgnome.blog.entities.Pojo;
import com.criticalgnome.blog.exceptions.DAOException;
import com.criticalgnome.blog.exceptions.ServiceException;

import java.util.List;

/**
 * Project Blog
 * Created on 22.03.2017.
 *
 * @author CriticalGnome
 */
public interface Service<T extends Pojo> {

    void create(T entity) throws DAOException, ServiceException;
    List<T> getAll() throws DAOException, ServiceException;
    T getById(int id) throws DAOException, ServiceException;
    void update(T entity) throws DAOException, ServiceException;
    void remove(int id) throws DAOException, ServiceException;
    int getMaxId() throws DAOException, ServiceException;
}
