package com.criticalgnome.blog.services;

import com.criticalgnome.blog.entities.Category;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.exceptions.ServiceException;

import java.util.List;

/**
 * Project Blog
 * Created on 13.04.2017.
 *
 * @author CriticalGnome
 */
public interface ICategoryService extends IService<Category> {

    Long create(Category category) throws DaoException, ServiceException;
    Category getById(Long id) throws DaoException, ServiceException;
    void update(Category category) throws DaoException, ServiceException;
    void remove(Long id) throws DaoException, ServiceException;
    List<Category> getAll() throws DaoException, ServiceException;
}
