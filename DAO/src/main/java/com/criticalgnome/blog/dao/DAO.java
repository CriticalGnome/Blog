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
public interface DAO<T extends Pojo> {
    void create(T pojo) throws DAOException;
    List<T> getAll() throws DAOException;
    T getById(int id) throws DAOException;
    void update(T pojo) throws DAOException;
    void remove(int id) throws DAOException;
    int getMaxId() throws DAOException;
}
