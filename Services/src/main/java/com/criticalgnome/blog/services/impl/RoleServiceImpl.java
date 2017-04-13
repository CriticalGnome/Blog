package com.criticalgnome.blog.services.impl;

import com.criticalgnome.blog.dao.IRoleDao;
import com.criticalgnome.blog.dao.impl.RoleDaoImpl;
import com.criticalgnome.blog.entities.Role;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.AbstractService;
import com.criticalgnome.blog.services.IRoleService;
import lombok.extern.log4j.Log4j2;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
public class RoleServiceImpl extends AbstractService<Role> implements IRoleService {

    private static volatile RoleServiceImpl instance;
    private static IRoleDao roleDao;

    private RoleServiceImpl(IRoleDao roleDao) { super(roleDao); }

    public static RoleServiceImpl getInstance() {
        if (instance == null) {
            synchronized (RoleServiceImpl.class) {
                if (instance == null) {
                    instance = new RoleServiceImpl(roleDao);
                }
            }
        }
        return instance;
    }

}
