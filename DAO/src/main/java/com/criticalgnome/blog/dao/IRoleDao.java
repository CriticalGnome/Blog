package com.criticalgnome.blog.dao;

import com.criticalgnome.blog.entities.Role;
import com.criticalgnome.blog.exceptions.DaoException;

/**
 * Project Blog
 * Created on 13.04.2017.
 *
 * @author CriticalGnome
 */
public interface IRoleDao extends IDao<Role> {
    /**
     * Get one row from table by name
     *
     * @param roleName tag mane
     * @return row object
     * @throws DaoException custom exception
     */
    public Role getByName(String roleName) throws DaoException;
}
