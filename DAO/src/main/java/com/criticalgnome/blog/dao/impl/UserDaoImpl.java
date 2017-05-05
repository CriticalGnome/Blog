package com.criticalgnome.blog.dao.impl;

import com.criticalgnome.blog.dao.IUserDao;
import com.criticalgnome.blog.entities.User;
import com.criticalgnome.blog.exceptions.DaoException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
@Repository
public class UserDaoImpl extends DaoImpl<User> implements IUserDao {

    @Autowired
    private UserDaoImpl(SessionFactory sessionFactory) {
        super(User.class, sessionFactory);
    }

    /**
     * Get one row from table by email and password
     *
     * @param email user email
     * @param password user password
     * @return row object or null if email/password not valid
     * @throws DaoException custom exception
     */
    public User getByEmailAndPassword(String email, String password) throws DaoException {
        try {
            Session session = currentSession();
            return (User) session.createCriteria(User.class)
                    .add(Restrictions.like("email", email))
                    .add(Restrictions.like("password", password))
                    .uniqueResult();
        } catch (HibernateException e) {
            throw new DaoException(UserDaoImpl.class, "Fatal error in getByEmailAndPassword user method", e);
        }
    }

    /**
     * Get one row from table by email
     *
     * @param email user email
     * @return row object or null if email/password not valid
     * @throws DaoException custom exception
     */
    public User getByEmail(String email) throws DaoException {
        try {
            Session session = currentSession();
            return (User) session.createCriteria(User.class)
                    .add(Restrictions.like("email", email))
                    .uniqueResult();
        } catch (HibernateException e) {
            throw new DaoException(UserDaoImpl.class, "Fatal error in getByEmail user method", e);
        }
    }
}
