package com.criticalgnome.blog.services.impl;

import com.criticalgnome.blog.dao.AbstractDao;
import com.criticalgnome.blog.dao.impl.RoleDaoImpl;
import com.criticalgnome.blog.entities.Role;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.AbstractService;
import com.criticalgnome.blog.services.IRoleService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
public class RoleServiceImpl extends AbstractService<Role> implements IRoleService {

    private static volatile RoleServiceImpl instance;
    private RoleDaoImpl roleDao = RoleDaoImpl.getInstance();

    private RoleServiceImpl() {}

    public static RoleServiceImpl getInstance() {
        if (instance == null) {
            synchronized (RoleServiceImpl.class) {
                if (instance == null) {
                    instance = new RoleServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public Long create(Role role) throws DaoException, ServiceException {
        Long id;
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            id = roleDao.create(role);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(RoleServiceImpl.class, "Transaction failed in create method", e);
        }
        return id;
    }

    @Override
    public Role getById(Long id) throws DaoException, ServiceException {
        Role role;
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            role = roleDao.getById(id);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(RoleServiceImpl.class, "Transaction failed in getById method", e);
        }
        return role;
    }

    @Override
    public void update(Role role) throws DaoException, ServiceException {
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            roleDao.update(role);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(RoleServiceImpl.class, "Transaction failed in update method", e);
        }
    }

    @Override
    public void remove(Long id) throws DaoException, ServiceException {
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            roleDao.remove(id);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(RoleServiceImpl.class, "Transaction failed in remove method", e);
        }
    }

    @Override
    public List<Role> getAll() throws DaoException, ServiceException {
        List<Role> roles;
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            roles = roleDao.getAll();
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(RoleServiceImpl.class, "Transaction failed in getAll method", e);
        }
        return roles;
    }

}
