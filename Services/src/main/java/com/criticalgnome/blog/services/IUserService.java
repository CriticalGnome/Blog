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

    /**
     * get User by email an password
     *
     * @param email email
     * @param password password
     * @return User
     * @throws ServiceException custom exception
     */
    User getByEmailAndPassword(String email, String password) throws ServiceException;

    /**
     * Get user by email
     *
     * @param email email
     * @return User
     * @throws ServiceException custom exception
     */
    User getByEmail(String email) throws ServiceException;
}
