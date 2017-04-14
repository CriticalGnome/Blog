package com.criticalgnome.blog.dao.impl;

import com.criticalgnome.blog.dao.IUserDao;
import com.criticalgnome.blog.entities.User;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
public class UserDaoImpl extends DaoImpl<User> implements IUserDao {

    private static volatile UserDaoImpl instance;

    private UserDaoImpl() {
        super(User.class);
    }

    /**
     * Singleton pattern
     * @return dao instance
     */
    public static UserDaoImpl getInstance() {
        if (instance == null) {
            synchronized (UserDaoImpl.class) {
                if (instance == null) {
                    instance = new UserDaoImpl();
                }
            }
        }
        return instance;
    }

    /**
     * Get one row from table by email and password
     * @param email user email
     * @param password user password
     * @return row object or null if email/password not valid
     * @throws DaoException custom exception
     */
    public User getByEmailAndPassword(String email, String password) throws DaoException {
        try {
            Session session = HibernateUtil.getInstance().getSession();
            return (User) session.createCriteria(User.class)
                    .add(Restrictions.eq("email", email))
                    .add(Restrictions.eq("password", password))
                    .uniqueResult();
        } catch (HibernateException e) {
            throw new DaoException(UserDaoImpl.class, "Fatal error in remove user method", e);
        }
    }
}
