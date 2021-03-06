package com.criticalgnome.blog.services;

import com.criticalgnome.blog.entities.Role;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.exceptions.ServiceException;

/**
 * Project Blog
 * Created on 13.04.2017.
 *
 * @author CriticalGnome
 */
public interface IRoleService extends IService<Role> {

    /**
     * Return role by name
     *
     * @param roleName name
     * @return role
     * @throws ServiceException custom exception
     */
    Role getByName(String roleName) throws ServiceException;

}
