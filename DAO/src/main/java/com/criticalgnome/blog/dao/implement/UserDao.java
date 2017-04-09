package com.criticalgnome.blog.dao.implement;

import com.criticalgnome.blog.dao.AbstractDao;
import com.criticalgnome.blog.entities.User;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.utils.HibernateUtil;
import lombok.extern.log4j.Log4j2;
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

    private UserDao() {
        super(User.class);
    }

    /**
     * Singleton pattern
     * @return dao instance
     */
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

    /**
     * Get one row from table by email and password
     * @param email user email
     * @param password user password
     * @return row object or null if email/password not valid
     * @throws DaoException custom exception
     */
    public User getByEmailAndPassword(String email, String password) throws DaoException {
        try {
            session = HibernateUtil.getInstance().getSession();
            return (User) session.createCriteria(User.class)
                    .add(Restrictions.like("email", email))
                    .add(Restrictions.like("password", password))
                    .uniqueResult();
        } catch (HibernateException e) {
            throw new DaoException(UserDao.class, "Fatal error in remove user method", e);
        }
    }
}
