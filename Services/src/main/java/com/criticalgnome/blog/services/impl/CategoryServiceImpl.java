package com.criticalgnome.blog.services.impl;

import com.criticalgnome.blog.dao.ICategoryDao;
import com.criticalgnome.blog.dao.IDao;
import com.criticalgnome.blog.dao.impl.CategoryDaoImpl;
import com.criticalgnome.blog.entities.Category;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.AbstractService;
import com.criticalgnome.blog.services.ICategoryService;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
public class CategoryServiceImpl extends AbstractService<Category> implements ICategoryService {

    private static volatile CategoryServiceImpl instance;
    private CategoryDaoImpl categoryDao = CategoryDaoImpl.getInstance();

    private CategoryServiceImpl() {
        super();
    }

    public static CategoryServiceImpl getInstance() {
        if (instance == null) {
            synchronized (CategoryServiceImpl.class) {
                if (instance == null) {
                    instance = new CategoryServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public Long create(Category category) throws DaoException, ServiceException {
        Long id;
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            id = categoryDao.create(category);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(CategoryServiceImpl.class, "Transaction failed in create method", e);
        }
        return id;
    }

    @Override
    public Category getById(Long id) throws DaoException, ServiceException {
        Category abstractEntitty;
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            abstractEntitty = categoryDao.getById(id);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(CategoryServiceImpl.class, "Transaction failed in getById method", e);
        }
        return abstractEntitty;
    }

    @Override
    public void update(Category category) throws DaoException, ServiceException {
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            categoryDao.update(category);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(CategoryServiceImpl.class, "Transaction failed in update method", e);
        }
    }

    @Override
    public void remove(Long id) throws DaoException, ServiceException {
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            categoryDao.remove(id);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(CategoryServiceImpl.class, "Transaction failed in remove method", e);
        }
    }

    @Override
    public List<Category> getAll() throws DaoException, ServiceException {
        List<Category> abstractEntities;
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            abstractEntities = categoryDao.getAll();
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(CategoryServiceImpl.class, "Transaction failed in getAll method", e);
        }
        return abstractEntities;
    }

}
