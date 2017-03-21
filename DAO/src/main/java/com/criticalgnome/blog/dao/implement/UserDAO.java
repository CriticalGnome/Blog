package com.criticalgnome.blog.dao.implement;

import com.criticalgnome.blog.dao.AbstractDAO;
import com.criticalgnome.blog.dao.exceptions.DAOException;
import com.criticalgnome.blog.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Project Blog
 * Created on 20.03.2017.
 *
 * @author CriticalGnome
 */
public class UserDAO extends AbstractDAO<User> {

    private static volatile UserDAO instance;
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void create(User user) throws DAOException {

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
    public void update(User user) throws DAOException {

    }

    @Override
    public void remove(int id) throws DAOException {

    }

    @Override
    public int getMaxId() throws DAOException {
        return 0;
    }
}
