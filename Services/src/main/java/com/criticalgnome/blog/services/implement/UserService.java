package com.criticalgnome.blog.services.implement;

import com.criticalgnome.blog.dao.implement.UserDao;
import com.criticalgnome.blog.entities.User;
import com.criticalgnome.blog.exceptions.DAOException;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.AbstractService;
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
    public Long create(User user) throws DAOException, ServiceException {
        return UserDao.getInstance().create(user);
    }

    @Override
    public User getById(Long id) throws DAOException, ServiceException {
        return UserDao.getInstance().getById(id);
    }

    @Override
    public void update(User user) throws DAOException, ServiceException {
        UserDao.getInstance().update(user);
    }

    @Override
    public void remove(Long id) throws DAOException, ServiceException {
        UserDao.getInstance().remove(id);
    }
}
