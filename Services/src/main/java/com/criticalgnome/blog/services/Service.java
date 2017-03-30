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

    Long create(T pojo) throws DAOException, ServiceException;
    T getById(Long id) throws DAOException, ServiceException;
    void update(T pojo) throws DAOException, ServiceException;
    void remove(Long id) throws DAOException, ServiceException;
}
