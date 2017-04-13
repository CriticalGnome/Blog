package com.criticalgnome.blog.services;

import com.criticalgnome.blog.dao.IDao;
import com.criticalgnome.blog.entities.AbstractEntity;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Project Blog
 * Created on 22.03.2017.
 *
 * @author CriticalGnome
 */
public abstract class AbstractService<T extends AbstractEntity> implements IService<T> {
    protected HibernateUtil util = HibernateUtil.getInstance();
    private IDao<T> dao;

    protected AbstractService(IDao<T> dao){
        this.dao = dao;
    }

    @Override
    public Long create(T abstractEntity) throws DaoException, ServiceException {
        Long id;
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            id = dao.create(abstractEntity);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(AbstractService.class, "Transaction failed in create method", e);
        }
        return id;
    }

    @Override
    public T getById(Long id) throws DaoException, ServiceException {
        T abstractEntitty;
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            abstractEntitty = dao.getById(id);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(AbstractService.class, "Transaction failed in getById method", e);
        }
        return abstractEntitty;
    }

    @Override
    public void update(T abstractEntitty) throws DaoException, ServiceException {
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            dao.update(abstractEntitty);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(AbstractService.class, "Transaction failed in update method", e);
        }
    }

    @Override
    public void remove(Long id) throws DaoException, ServiceException {
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            dao.remove(id);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(AbstractService.class, "Transaction failed in remove method", e);
        }
    }

    @Override
    public List<T> getAll() throws DaoException, ServiceException {
        List<T> abstractEntities;
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            abstractEntities = dao.getAll();
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(AbstractService.class, "Transaction failed in getAll method", e);
        }
        return abstractEntities;
    }

}
