package com.criticalgnome.blog.services.impl;

import com.criticalgnome.blog.dao.impl.UserDaoImpl;
import com.criticalgnome.blog.entities.User;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.utils.MD5;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
public class UserServiceImpl extends ServiceImpl<User> {

    private static volatile UserServiceImpl instance;
    private UserDaoImpl userDao = UserDaoImpl.getInstance();

    private UserServiceImpl() { super(); }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            synchronized (UserServiceImpl.class) {
                if (instance == null) {
                    instance = new UserServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public Long create(User user) throws DaoException, ServiceException {
        user.setPassword(MD5.md5Encode(user.getPassword()));
        Long id;
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            id = userDao.create(user);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(UserServiceImpl.class, "Transaction failed in create method", e);
        }
        return id;
    }

    @Override
    public User getById(Long id) throws DaoException, ServiceException {
        User abstractEntitty;
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            abstractEntitty = userDao.getById(id);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(UserServiceImpl.class, "Transaction failed in getById method", e);
        }
        return abstractEntitty;
    }

    @Override
    public void update(User user) throws DaoException, ServiceException {
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            userDao.update(user);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(UserServiceImpl.class, "Transaction failed in update method", e);
        }
    }

    @Override
    public void remove(Long id) throws DaoException, ServiceException {
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            userDao.remove(id);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(UserServiceImpl.class, "Transaction failed in remove method", e);
        }
    }

    @Override
    public List<User> getAll() throws DaoException, ServiceException {
        List<User> abstractEntities;
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            abstractEntities = userDao.getAll();
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(UserServiceImpl.class, "Transaction failed in getAll method", e);
        }
        return abstractEntities;
    }

    public User getByEmailAndPassword(String email, String password) throws DaoException, ServiceException {
        User user;
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            user = UserDaoImpl.getInstance().getByEmailAndPassword(email, MD5.md5Encode(password));
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(UserServiceImpl.class, "Transaction failed in getByEmailAndPassword method", e);
        }
        return user;
    }
}
