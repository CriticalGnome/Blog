package com.criticalgnome.blog.dao.implement;

import com.criticalgnome.blog.dao.AbstractDao;
import com.criticalgnome.blog.entities.Category;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.utils.HibernateConnector;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.hibernate.HibernateException;

import java.util.List;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
@Log4j2
public class CategoryDao extends AbstractDao<Category> {

    private static volatile CategoryDao instance;

    private CategoryDao() {}

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

    @Override
    public Long create(Category category) throws DaoException {
        try {
            session = HibernateConnector.getInstance().getSession();
            session.beginTransaction();
            Long id = (Long) session.save(category);
            session.getTransaction().commit();
            log.log(Level.INFO, "New category created [{}] {}", category.getId(), category.getName());
            return id;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            String message = "Fatal error in create category method";
            log.log(Level.ERROR, message);
            throw new DaoException(message, e);
        } finally {
            session.close();
        }
    }

    @Override
    public Category getById(Long id) throws DaoException {
        try {
            session = HibernateConnector.getInstance().getSession();
            session.beginTransaction();
            Category category = (Category) session.get(Category.class, id);
            session.getTransaction().commit();
            return category;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            String message = "Fatal error in get category method";
            log.log(Level.ERROR, message);
            throw new DaoException(message, e);
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Category category) throws DaoException {
        try {
            session = HibernateConnector.getInstance().getSession();
            session.beginTransaction();
            session.update(category);
            session.getTransaction().commit();
            log.log(Level.INFO, "Category updated [{}] {}", category.getId(), category.getName());
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            String message = "Fatal error in update category method";
            log.log(Level.ERROR, message);
            throw new DaoException(message, e);
        } finally {
            session.close();
        }
    }

    @Override
    public void remove(Long id) throws DaoException {
        try {
            Category category = getById(id);
            session = HibernateConnector.getInstance().getSession();
            session.beginTransaction();
            session.delete(category);
            session.getTransaction().commit();
            log.log(Level.INFO, "Category removed [{}] {}", category.getId(), category.getName());
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            String message = "Fatal error in remove category method";
            log.log(Level.ERROR, message);
            throw new DaoException(message, e);
        } finally {
            session.close();
        }
    }

    public List<Category> getAll() throws DaoException {
        try {
            session = HibernateConnector.getInstance().getSession();
            return session.createCriteria(Category.class)
                    .list();
        } catch (HibernateException e) {
            String message = "Fatal error in getAll method";
            log.log(Level.ERROR, message);
            throw new DaoException(message, e);
        } finally {
            session.close();
        }
    }
}
