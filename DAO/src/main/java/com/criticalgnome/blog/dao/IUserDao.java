package com.criticalgnome.blog.dao;

import com.criticalgnome.blog.entities.User;
import com.criticalgnome.blog.exceptions.DaoException;

/**
 * Project Blog
 * Created on 13.04.2017.
 *
 * @author CriticalGnome
 */
public interface IUserDao extends IDao<User> {

    /**
     * Get one row from table by email and password
     * @param email user email
     * @param password user password
     * @return row object or null if email/password not valid
     * @throws DaoException custom exception
     */
    User getByEmailAndPassword(String email, String password) throws DaoException;

    /**
     * Get one row from table by email
     * @param email user email
     * @return row object or null if email/password not valid
     * @throws DaoException custom exception
     */
    User getByEmail(String email) throws DaoException;
}
