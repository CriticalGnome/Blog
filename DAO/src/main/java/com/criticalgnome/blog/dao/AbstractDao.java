package com.criticalgnome.blog.dao;

import com.criticalgnome.blog.entities.Pojo;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
public abstract class AbstractDao<T extends Pojo> implements Dao<T> {
    protected HibernateUtil util = HibernateUtil.getInstance();
    protected Session session;
    protected Criteria criteria;
    private Class persistentClass;

    protected AbstractDao(Class persistentClass){
        this.persistentClass = persistentClass;
    }

    /**
     * Create new row in table
     * @param pojo object
     * @return id for created row
     * @throws DaoException custom exception
     */
    @Override
    public Long create(T pojo) throws DaoException {
        Long id;
        try {
            session = util.getSession();
            id = (Long) session.save(pojo);
        } catch (HibernateException e) {
            throw new DaoException(persistentClass, "Fatal error in create category method", e);
        }
        return id;

    }

    /**
     * Get one row from table by id
     * @param id row id
     * @return row object
     * @throws DaoException custom exception
     */
    @Override
    public T getById(Long id) throws DaoException {
        try {
            session = util.getSession();
            return (T) session.get(persistentClass, id);
        } catch (HibernateException e) {
            throw new DaoException(persistentClass, "Fatal error in get category method", e);
        }
    }

    /**
     * Update object data in table
     * @param pojo object
     * @throws DaoException custom exception
     */
    @Override
    public void update(T pojo) throws DaoException {
        try {
            session = util.getSession();
            session.update(pojo);
        } catch (HibernateException e) {
            throw new DaoException(persistentClass, "Fatal error in update category method", e);
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
            session = util.getSession();
            T pojo = getById(id);
            session.delete(pojo);
        } catch (HibernateException e) {
            throw new DaoException(persistentClass, "Fatal error in remove category method", e);
        }
    }

    /**
     * Get list of all categories
     * @return list
     * @throws DaoException custom exception
     */
    public List<T> getAll() throws DaoException {
        try {
            session = util.getSession();
            criteria = session.createCriteria(persistentClass);
            return criteria.list();
        } catch (HibernateException e) {
            throw new DaoException(persistentClass, "Fatal error in getAll method", e);
        }
    }


}
