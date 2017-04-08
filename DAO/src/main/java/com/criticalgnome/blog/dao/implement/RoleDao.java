package com.criticalgnome.blog.dao.implement;

import com.criticalgnome.blog.dao.AbstractDao;
import com.criticalgnome.blog.entities.Role;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.utils.HibernateConnector;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.hibernate.HibernateException;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
@Log4j2
public class RoleDao extends AbstractDao<Role> {

    private static volatile RoleDao instance;

    private RoleDao() {}

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

    /**
     * Create new row in table
     * @param role object
     * @return id for created row
     * @throws DaoException custom exception
     */
    @Override
    public Long create(Role role) throws DaoException {
        try {
            session = HibernateConnector.getInstance().getSession();
            session.beginTransaction();
            Long id = (Long) session.save(role);
            session.getTransaction().commit();
            log.log(Level.INFO, "New role created [{}] {}", role.getId(), role.getName());
            return id;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            throw new DaoException(RoleDao.class, "Fatal error in create role method", e);
        } finally {
            session.close();
        }
    }

    /**
     * Get one row from table by id
     * @param id row id
     * @return row object
     * @throws DaoException custom exception
     */
    @Override
    public Role getById(Long id) throws DaoException {
        try {
            session = HibernateConnector.getInstance().getSession();
            session.beginTransaction();
            Role role = (Role) session.get(Role.class, id);
            session.getTransaction().commit();
            return role;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            throw new DaoException(RoleDao.class, "Fatal error in get role method", e);
        } finally {
            session.close();
        }

    }

    /**
     * Update object data in table
     * @param role object
     * @throws DaoException custom exception
     */
    @Override
    public void update(Role role) throws DaoException {
        try {
            session = HibernateConnector.getInstance().getSession();
            session.beginTransaction();
            session.update(role);
            session.getTransaction().commit();
            log.log(Level.INFO, "Role updated [{}] {}", role.getId(), role.getName());
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            throw new DaoException(RoleDao.class, "Fatal error in update role method", e);
        } finally {
            session.close();
        }
    }

    /**
     * Remove row from table by id
     * @param id id
     * @throws DaoException custom exception
     */
    @Override
    public void remove(Long id) throws DaoException {
        try {
            Role role = getById(id);
            session = HibernateConnector.getInstance().getSession();
            session.beginTransaction();
            session.delete(role);
            session.getTransaction().commit();
            log.log(Level.INFO, "Role removed [{}] {}", role.getId(), role.getName());
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            throw new DaoException(RoleDao.class, "Fatal error in remove role method", e);
        } finally {
            session.close();
        }
    }

}
