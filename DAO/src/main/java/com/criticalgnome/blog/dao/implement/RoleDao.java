package com.criticalgnome.blog.dao.implement;

import com.criticalgnome.blog.dao.AbstractDao;
import com.criticalgnome.blog.entities.Role;
import com.criticalgnome.blog.exceptions.DAOException;
import com.criticalgnome.blog.utils.HibernateConnector;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
@Log4j2
public class RoleDao extends AbstractDao<Role> {

    @Override
    public Long create(Role role) throws DAOException {
        try {
            session = HibernateConnector.getInstance().getSession();
            session.beginTransaction();
            Long id = (Long) session.save(role);
            session.getTransaction().commit();
            log.log(Level.INFO, "New role created [{}] {}", role.getId(), role.getName());
            return id;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            log.log(Level.ERROR, "Fatal error in create role method");
            throw new DAOException("Fatal error in create role method", e);
        } finally {
            session.close();
        }
    }

    @Override
    public Role getById(Long id) throws DAOException {
        try {
            session = HibernateConnector.getInstance().getSession();
            session.beginTransaction();
            Role role = (Role) session.get(Role.class, id);
            session.getTransaction().commit();
            return role;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            log.log(Level.ERROR, "Fatal error in get role method");
            throw new DAOException("Fatal error in get role method", e);
        } finally {
            session.close();
        }

    }

    @Override
    public void update(Role role) throws DAOException {
        try {
            session = HibernateConnector.getInstance().getSession();
            session.beginTransaction();
            session.update(role);
            session.getTransaction().commit();
            log.log(Level.INFO, "Role updated [{}] {}", role.getId(), role.getName());
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            log.log(Level.ERROR, "Fatal error in update role method");
            throw new DAOException("Fatal error in update role method", e);
        } finally {
            session.close();
        }
    }

    @Override
    public void remove(Long id) throws DAOException {
        try {
            session = HibernateConnector.getInstance().getSession();
            session.beginTransaction();
            Role role = getById(id);
            session.getTransaction().commit();
            session.beginTransaction();
            session.delete(role);
            session.getTransaction().commit();
            log.log(Level.INFO, "Role removed [{}] {}", role.getId(), role.getName());
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            log.log(Level.ERROR, "Fatal error in remove role method");
            throw new DAOException("Fatal error in remove role method", e);
        } finally {
            session.close();
        }
    }

}
