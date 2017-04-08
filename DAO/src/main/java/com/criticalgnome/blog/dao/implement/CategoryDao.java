package com.criticalgnome.blog.dao.implement;

import com.criticalgnome.blog.dao.AbstractDao;
import com.criticalgnome.blog.entities.Category;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.utils.HibernateConnector;
import org.hibernate.HibernateException;

import java.util.List;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
public class CategoryDao extends AbstractDao<Category> {

    private static volatile CategoryDao instance;
    private HibernateConnector connector = HibernateConnector.getInstance();

    private CategoryDao() {}

    /**
     * Singleton pattern
     * @return dao instance
     */
    public static CategoryDao getInstance() {
        if (instance == null) {
            synchronized (CategoryDao.class) {
                if (instance == null) {
                    instance = new CategoryDao();
                }
            }
        }
        return instance;
    }

    /**
     * Create new row in table
     * @param category object
     * @return id for created row
     * @throws DaoException custom exception
     */
    @Override
    public Long create(Category category) throws DaoException {
        try {
            session = connector.getSession();
            return (Long) session.save(category);
        } catch (HibernateException e) {
            throw new DaoException(CategoryDao.class, "Fatal error in create category method", e);
        }
    }

    /**
     * Get one row from table by id
     * @param id row id
     * @return row object
     * @throws DaoException custom exception
     */
    @Override
    public Category getById(Long id) throws DaoException {
        try {
            session = connector.getSession();
            return (Category) session.get(Category.class, id);
        } catch (HibernateException e) {
            throw new DaoException(CategoryDao.class, "Fatal error in get category method", e);
        }
    }

    /**
     * Update object data in table
     * @param category object
     * @throws DaoException custom exception
     */
    @Override
    public void update(Category category) throws DaoException {
        try {
            session = connector.getSession();
            session.update(category);
        } catch (HibernateException e) {
            throw new DaoException(CategoryDao.class, "Fatal error in update category method", e);
        }
    }

    /**
     * Remove row from table by id
     * @param id id
     * @throws DaoException custom exception
     */
    @Override
    public void remove(Long id) throws DaoException {
        try {
            session = connector.getSession();
            Category category = getById(id);
            session.delete(category);
        } catch (HibernateException e) {
            throw new DaoException(CategoryDao.class, "Fatal error in remove category method", e);
        }
    }

    /**
     * Get list of all categories
     * @return list
     * @throws DaoException custom exception
     */
    public List<Category> getAll() throws DaoException {
        try {
            session = connector.getSession();
            criteria = session.createCriteria(Category.class);
            return criteria.list();
        } catch (HibernateException e) {
            throw new DaoException(CategoryDao.class, "Fatal error in getAll method", e);
        }
    }
}