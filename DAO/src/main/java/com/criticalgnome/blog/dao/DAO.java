package com.criticalgnome.blog.dao;

import com.criticalgnome.blog.dao.exceptions.DAOException;
import com.criticalgnome.blog.entities.Entity;

import java.util.List;

/**
 * Project Blog
 * Created on 20.03.2017.
 *
 * @author CriticalGnome
 */
public interface DAO<T extends Entity> {
    void create(T entity) throws DAOException;
    List<T> getAll() throws DAOException;
    T getById(int id) throws DAOException;
    void update(T entity) throws DAOException;
    void remove(int id) throws DAOException;
    int getMaxId() throws DAOException;
}
