package com.criticalgnome.blog.dao.implement;

import com.criticalgnome.blog.dao.AbstractDAO;
import com.criticalgnome.blog.dao.exceptions.DAOException;
import com.criticalgnome.blog.entities.Role;

import java.util.List;

/**
 * Project Blog
 * Created on 20.03.2017.
 *
 * @author CriticalGnome
 */
public class RoleDAO extends AbstractDAO<Role> {

    @Override
    public void create(Role entity) throws DAOException {

    }

    @Override
    public List<Role> getAll() throws DAOException {
        return null;
    }

    @Override
    public Role getById(int id) throws DAOException {
        return null;
    }

    @Override
    public void update(Role entity) throws DAOException {

    }

    @Override
    public void remove(int id) throws DAOException {

    }

    @Override
    public int getMaxId() throws DAOException {
        return 0;
    }
}
