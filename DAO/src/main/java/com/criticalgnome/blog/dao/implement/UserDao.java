package com.criticalgnome.blog.dao.implement;

import com.criticalgnome.blog.dao.AbstractDao;
import com.criticalgnome.blog.entities.User;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.utils.HibernateConnector;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
@Log4j2
public class UserDao extends AbstractDao<User> {

    private static volatile UserDao instance;

    private UserDao() {}

    public static UserDao getInstance() {
        if (instance == null) {
            synchronized (UserDao.class) {
                if (instance == null) {
                    instance = new UserDao();
                }
            }
        }
        return instance;
    }

    @Override
    public Long create(User user) throws DaoException {
        try {
            session = HibernateConnector.getInstance().getSession();
            session.beginTransaction();
            Long id = (Long) session.save(user);
            session.getTransaction().commit();
            log.log(Level.INFO, "New user created [{}] {}", user.getId(), user.getNickName());
            return id;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            String message = "Fatal error in create user method";
            log.log(Level.ERROR, message);
            throw new DaoException(message, e);
        } finally {
            session.close();
        }
    }

    @Override
    public User getById(Long id) throws DaoException {
        try {
            session = HibernateConnector.getInstance().getSession();
            session.beginTransaction();
            User user = (User) session.get(User.class, id);
            session.getTransaction().commit();
            return user;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            String message = "Fatal error in get user method";
            log.log(Level.ERROR, message);
            throw new DaoException(message, e);
        } finally {
            session.close();
        }
    }

    @Override
    public void update(User user) throws DaoException {
        try {
            session = HibernateConnector.getInstance().getSession();
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
            log.log(Level.INFO, "User updated [{}] {}", user.getId(), user.getNickName());
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            String message = "Fatal error in update user method";
            log.log(Level.ERROR, message);
            throw new DaoException(message, e);
        } finally {
            session.close();
        }
    }

    @Override
    public void remove(Long id) throws DaoException {
        try {
            User user = getById(id);
            session = HibernateConnector.getInstance().getSession();
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
            log.log(Level.INFO, "User removed [{}] {}", user.getId(), user.getNickName());
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            String message = "Fatal error in remove user method";
            log.log(Level.ERROR, message);
            throw new DaoException(message, e);
        } finally {
            session.close();
        }
    }

    public User getByEmailAndPassword(String email, String password) throws DaoException {
        try {
            session = HibernateConnector.getInstance().getSession();
            return (User) session.createCriteria(User.class)
                    .add(Restrictions.like("email", email))
                    .add(Restrictions.like("password", password))
                    .uniqueResult();
        } catch (HibernateException e) {
            String message = "Fatal error in remove user method";
            log.log(Level.ERROR, message);
            throw new DaoException(message, e);
        } finally {
            session.close();
        }
    }
}
