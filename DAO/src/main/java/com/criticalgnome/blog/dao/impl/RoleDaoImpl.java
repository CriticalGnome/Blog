package com.criticalgnome.blog.dao.impl;

import com.criticalgnome.blog.dao.AbstractDao;
import com.criticalgnome.blog.dao.IRoleDao;
import com.criticalgnome.blog.entities.Role;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
public class RoleDaoImpl extends AbstractDao<Role> implements IRoleDao {

    private static volatile RoleDaoImpl instance;

    private RoleDaoImpl() {
        super(Role.class);
    }

    /**
     * Singleton pattern
     * @return dao instance
     */
    public static RoleDaoImpl getInstance() {
        if (instance == null) {
            synchronized (RoleDaoImpl.class) {
                if (instance == null) {
                    instance = new RoleDaoImpl();
                }
            }
        }
        return instance;
    }

}
