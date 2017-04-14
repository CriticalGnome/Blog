package com.criticalgnome.blog.services.impl;

import com.criticalgnome.blog.dao.IDao;
import com.criticalgnome.blog.entities.AbstractEntity;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.IService;
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
public abstract class ServiceImpl<T extends AbstractEntity> implements IService<T> {
    protected HibernateUtil util = HibernateUtil.getInstance();

    private IDao<T> iDao;

    public ServiceImpl(IDao<T> iDao) {
        this.iDao = iDao;
    }

    public ServiceImpl() {}

    @Override
    public Long create(T abstractEntity) throws DaoException, ServiceException {
        Long id;
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            id = iDao.create(abstractEntity);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(ServiceImpl.class, "Transaction failed in create method", e);
        }
        return id;
    }

    @Override
    public T getById(Long id) throws DaoException, ServiceException {
        T abstractEntitty;
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            abstractEntitty = iDao.getById(id);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(ServiceImpl.class, "Transaction failed in getById method", e);
        }
        return abstractEntitty;
    }

    @Override
    public void update(T abstractEntity) throws DaoException, ServiceException {
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            iDao.update(abstractEntity);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(ServiceImpl.class, "Transaction failed in update method", e);
        }
    }

    @Override
    public void remove(Long id) throws DaoException, ServiceException {
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            iDao.remove(id);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(ServiceImpl.class, "Transaction failed in remove method", e);
        }
    }

    @Override
    public List<T> getAll() throws DaoException, ServiceException {
        List<T> abstractEntities;
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            abstractEntities = iDao.getAll();
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(CategoryServiceImpl.class, "Transaction failed in getAll method", e);
        }
        return abstractEntities;
    }

}
