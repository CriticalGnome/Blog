package com.criticalgnome.blog.dao.implement;

import com.criticalgnome.blog.dao.AbstractDAO;
import com.criticalgnome.blog.dao.exceptions.DAOException;
import com.criticalgnome.blog.entities.User;

import java.util.List;

/**
 * Project Blog
 * Created on 20.03.2017.
 *
 * @author CriticalGnome
 */
public class UserDAO extends AbstractDAO<User> {

    @Override
    public void create(User entity) throws DAOException {

    }

    @Override
    public List<User> getAll() throws DAOException {
        return null;
    }

    @Override
    public User getById(int id) throws DAOException {
        return null;
    }

    @Override
    public void update(User entity) throws DAOException {

    }

    @Override
    public void remove(int id) throws DAOException {

    }

    @Override
    public int getMaxId() throws DAOException {
        return 0;
    }
}
