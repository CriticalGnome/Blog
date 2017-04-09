package com.criticalgnome.blog.services.implement;

import com.criticalgnome.blog.dao.implement.UserDao;
import com.criticalgnome.blog.entities.User;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.AbstractService;
import com.criticalgnome.blog.utils.MD5;
import lombok.extern.log4j.Log4j2;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
@Log4j2
public class UserService extends AbstractService<User> {

    private static volatile UserService instance;
    private UserDao userDao = UserDao.getInstance();

    private UserService() {}

    public static UserService getInstance() {
        if (instance == null) {
            synchronized (UserService.class) {
                if (instance == null) {
                    instance = new UserService();
                }
            }
        }
        return instance;
    }

    @Override
    public Long create(User user) throws DaoException, ServiceException {
        user.setPassword(MD5.md5Encode(user.getPassword()));
        Long id;
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            id = userDao.create(user);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(UserService.class, "Transaction failed in create method", e);
        }
        return id;
    }

    @Override
    public User getById(Long id) throws DaoException, ServiceException {
        User user;
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            user = userDao.getById(id);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(UserService.class, "Transaction failed in getById method", e);
        }
        return user;
    }

    @Override
    public void update(User user) throws DaoException, ServiceException {
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            userDao.update(user);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(UserService.class, "Transaction failed in update method", e);
        }
    }

    @Override
    public void remove(Long id) throws DaoException, ServiceException {
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            userDao.remove(id);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(UserService.class, "Transaction failed in remove method", e);
        }
    }

    public User getByEmailAndPassword(String email, String password) throws DaoException, ServiceException {
        User user;
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            user = userDao.getByEmailAndPassword(email, MD5.md5Encode(password));
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(UserService.class, "Transaction failed in getByEmailAndPassword method", e);
        }
        return user;
    }
}
