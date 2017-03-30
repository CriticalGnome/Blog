package com.criticalgnome.blog.dao;

import com.criticalgnome.blog.exceptions.DAOException;
import com.criticalgnome.blog.entities.Pojo;

import java.util.List;

/**
 * Project Blog
 * Created on 20.03.2017.
 *
 * @author CriticalGnome
 */
public interface Dao<T extends Pojo> {
    Long create(T pojo) throws DAOException;
    T getById(Long id) throws DAOException;
    void update(T pojo) throws DAOException;
    void remove(Long id) throws DAOException;
}
