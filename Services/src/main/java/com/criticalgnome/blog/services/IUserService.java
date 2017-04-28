package com.criticalgnome.blog.services;

import com.criticalgnome.blog.entities.User;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.exceptions.ServiceException;

/**
 * Project Blog
 * Created on 13.04.2017.
 *
 * @author CriticalGnome
 */
public interface IUserService extends IService<User> {
    User getByEmailAndPassword(String email, String password) throws ServiceException;

    User getByEmail(String email) throws ServiceException;
}
