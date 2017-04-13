package com.criticalgnome.blog.services.impl;

import com.criticalgnome.blog.dao.IUserDao;
import com.criticalgnome.blog.dao.impl.UserDaoImpl;
import com.criticalgnome.blog.entities.User;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.AbstractService;
import com.criticalgnome.blog.utils.MD5;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
public class UserServiceImpl extends AbstractService<User> {

    private static volatile UserServiceImpl instance;
    private static IUserDao userDao;

    private UserServiceImpl(IUserDao userDao) { super(userDao); }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            synchronized (UserServiceImpl.class) {
                if (instance == null) {
                    instance = new UserServiceImpl(userDao);
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
            id = UserDaoImpl.getInstance().create(user);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(UserServiceImpl.class, "Transaction failed in create method", e);
        }
        return id;
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
