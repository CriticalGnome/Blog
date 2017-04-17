package com.criticalgnome.blog.dao.impl;

import com.criticalgnome.blog.dao.IDao;
import com.criticalgnome.blog.entities.AbstractEntity;
import com.criticalgnome.blog.exceptions.DaoException;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
@Repository
public abstract class DaoImpl<T extends AbstractEntity> implements IDao<T> {

    @Autowired
    private SessionFactory sessionFactory;
    private Class persistentClass;

    Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    protected DaoImpl(Class persistentClass, SessionFactory sessionFactory){
        this.persistentClass = persistentClass;
        this.sessionFactory = sessionFactory;
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
            Session session = currentSession();
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
            Session session = currentSession();
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
            Session session = currentSession();
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
            Session session = currentSession();
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
            Session session = currentSession();
            Criteria criteria = session.createCriteria(persistentClass);
            return criteria.list();
        } catch (HibernateException e) {
            throw new DaoException(persistentClass, "Fatal error in getAll method", e);
        }
    }


}
