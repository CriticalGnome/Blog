package com.criticalgnome.blog.services.implement;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */

import com.criticalgnome.blog.dao.implement.RoleDao;
import com.criticalgnome.blog.entities.Role;
import com.criticalgnome.blog.exceptions.DAOException;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.AbstractService;
import lombok.extern.log4j.Log4j2;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
@Log4j2
public class RoleService extends AbstractService<Role> {

    private static volatile RoleService instance;

    private RoleService() {}

    public static RoleService getInstance() {
        if (instance == null) {
            synchronized (RoleService.class) {
                if (instance == null) {
                    instance = new RoleService();
                }
            }
        }
        return instance;
    }

    @Override
    public Long create(Role role) throws DAOException, ServiceException {
        return RoleDao.getInstance().create(role);
    }

    @Override
    public Role getById(Long id) throws DAOException, ServiceException {
        return RoleDao.getInstance().getById(id);
    }

    @Override
    public void update(Role role) throws DAOException, ServiceException {
        RoleDao.getInstance().update(role);
    }

    @Override
    public void remove(Long id) throws DAOException, ServiceException {
        RoleDao.getInstance().remove(id);
    }
}
