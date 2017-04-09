package com.criticalgnome.blog.services.implement;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */

import com.criticalgnome.blog.dao.implement.RoleDao;
import com.criticalgnome.blog.entities.Role;
import com.criticalgnome.blog.exceptions.DaoException;
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
    private RoleDao roleDao = RoleDao.getInstance();

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
    public Long create(Role role) throws DaoException, ServiceException {
        Long id;
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            id = roleDao.create(role);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(RoleService.class, "Transaction failed in create method", e);
        }
        return id;
    }

    @Override
    public Role getById(Long id) throws DaoException, ServiceException {
        Role role;
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            role = roleDao.getById(id);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(RoleService.class, "Transaction failed in getById method", e);
        }
        return role;
    }

    @Override
    public void update(Role role) throws DaoException, ServiceException {
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            roleDao.update(role);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(RoleService.class, "Transaction failed in update method", e);
        }
    }

    @Override
    public void remove(Long id) throws DaoException, ServiceException {
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            roleDao.remove(id);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(RoleService.class, "Transaction failed in remove method", e);
        }
    }
}
