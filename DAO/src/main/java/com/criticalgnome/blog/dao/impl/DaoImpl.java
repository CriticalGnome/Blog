package com.criticalgnome.blog.dao.impl;

import com.criticalgnome.blog.dao.IDao;
import com.criticalgnome.blog.entities.AbstractEntity;
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
public abstract class DaoImpl<T extends AbstractEntity> implements IDao<T> {
    protected HibernateUtil util = HibernateUtil.getInstance();
    private Class persistentClass;

    protected DaoImpl(Class persistentClass){
        this.persistentClass = persistentClass;
    }

    /**
     * Create new row in table
     * @param abstractEntity object
     * @return id for created row
     * @throws DaoException custom exception
     */
    @Override
    public Long create(T abstractEntity) throws DaoException {
        Long id;
        try {
            Session session = util.getSession();
            id = (Long) session.save(abstractEntity);
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
            Session session = util.getSession();
            return (T) session.get(persistentClass, id);
        } catch (HibernateException e) {
            throw new DaoException(persistentClass, "Fatal error in get category method", e);
        }
    }

    /**
     * Update object data in table
     * @param abstractEntity object
     * @throws DaoException custom exception
     */
    @Override
    public void update(T abstractEntity) throws DaoException {
        try {
            Session session = util.getSession();
            session.update(abstractEntity);
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
            Session session = util.getSession();
            T abstractEntity = getById(id);
            session.delete(abstractEntity);
        } catch (HibernateException e) {
            throw new DaoException(persistentClass, "Fatal error in remove category method", e);
        }
    }

    /**
     * Get list of all categories
     * @return list
     * @throws DaoException custom exception
     */
    @Override
    public List<T> getAll() throws DaoException {
        try {
            Session session = util.getSession();
            Criteria criteria = session.createCriteria(persistentClass);
            return criteria.list();
        } catch (HibernateException e) {
            throw new DaoException(persistentClass, "Fatal error in getAll method", e);
        }
    }


}
