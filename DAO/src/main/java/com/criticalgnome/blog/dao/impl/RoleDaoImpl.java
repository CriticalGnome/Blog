package com.criticalgnome.blog.dao.impl;

import com.criticalgnome.blog.dao.IRoleDao;
import com.criticalgnome.blog.entities.Role;
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
public class RoleDaoImpl extends DaoImpl<Role> implements IRoleDao {

    @Autowired
    private RoleDaoImpl(SessionFactory sessionFactory) {
        super(Role.class, sessionFactory);
    }

    /**
     * Get one row from table by name
     *
     * @param roleName tag mane
     * @return row object
     * @throws DaoException custom exception
     */
    @Override
    public Role getByName(String roleName) throws DaoException {
        try {
            Session session = currentSession();
            return (Role) session.createCriteria(Role.class)
                    .add(Restrictions.eq("name", roleName))
                    .uniqueResult();
        } catch (HibernateException e) {
            throw new DaoException(RoleDaoImpl.class, "Fatal error in getByName method", e);
        }
    }
}
