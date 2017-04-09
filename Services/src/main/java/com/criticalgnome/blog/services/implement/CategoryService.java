package com.criticalgnome.blog.services.implement;

import com.criticalgnome.blog.dao.implement.CategoryDao;
import com.criticalgnome.blog.entities.Category;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.AbstractService;
import com.criticalgnome.blog.utils.HibernateUtil;
import lombok.extern.log4j.Log4j2;

import java.util.List;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
@Log4j2
public class CategoryService extends AbstractService<Category> {

    private static volatile CategoryService instance;
    private CategoryDao categoryDao = CategoryDao.getInstance();

    private CategoryService() {};

    public static CategoryService getInstance() {
        if (instance == null) {
            synchronized (CategoryService.class) {
                if (instance == null) {
                    instance = new CategoryService();
                }
            }
        }
        return instance;
    }

    @Override
    public Long create(Category category) throws DaoException, ServiceException {
        Long id;
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            id = categoryDao.create(category);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(CategoryService.class, "Transaction failed in create method", e);
        }
        return id;
    }

    @Override
    public Category getById(Long id) throws DaoException, ServiceException {
        Category category;
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            category = categoryDao.getById(id);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(CategoryService.class, "Transaction failed in getById method", e);
        }
        return category;
    }

    @Override
    public void update(Category category) throws DaoException, ServiceException {
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            categoryDao.update(category);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(CategoryService.class, "Transaction failed in update method", e);
        }
    }

    @Override
    public void remove(Long id) throws DaoException, ServiceException {
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            categoryDao.remove(id);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(CategoryService.class, "Transaction failed in remove method", e);
        }
    }

    public List<Category> getAll() throws DaoException, ServiceException {
        List<Category> categories;
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            categories = categoryDao.getAll();
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(CategoryService.class, "Transaction failed in getAll method", e);
        }
        return categories;
    }
}
