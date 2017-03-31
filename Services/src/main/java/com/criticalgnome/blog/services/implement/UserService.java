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
        return UserDao.getInstance().create(user);
    }

    @Override
    public User getById(Long id) throws DaoException, ServiceException {
        return UserDao.getInstance().getById(id);
    }

    @Override
    public void update(User user) throws DaoException, ServiceException {
        UserDao.getInstance().update(user);
    }

    @Override
    public void remove(Long id) throws DaoException, ServiceException {
        UserDao.getInstance().remove(id);
    }

    public User getByEmailAndPassword(String email, String password) throws DaoException, ServiceException {
        return UserDao.getInstance().getByEmailAndPassword(email, MD5.md5Encode(password));
    }
}
