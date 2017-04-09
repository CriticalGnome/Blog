package com.criticalgnome.blog.dao.implement;

import com.criticalgnome.blog.dao.AbstractDao;
import com.criticalgnome.blog.entities.Role;
import lombok.extern.log4j.Log4j2;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
@Log4j2
public class RoleDao extends AbstractDao<Role> {

    private static volatile RoleDao instance;

    private RoleDao() {
        super(Role.class);
    }

    /**
     * Singleton pattern
     * @return dao instance
     */
    public static RoleDao getInstance() {
        if (instance == null) {
            synchronized (RoleDao.class) {
                if (instance == null) {
                    instance = new RoleDao();
                }
            }
        }
        return instance;
    }

}
