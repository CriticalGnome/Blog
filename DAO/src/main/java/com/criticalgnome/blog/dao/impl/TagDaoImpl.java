package com.criticalgnome.blog.dao.impl;

import com.criticalgnome.blog.dao.ITagDao;
import com.criticalgnome.blog.entities.Tag;
import com.criticalgnome.blog.exceptions.DaoException;
import org.hibernate.HibernateException;
import org.hibernate.NonUniqueResultException;
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
public class TagDaoImpl extends DaoImpl<Tag> implements ITagDao {

    @Autowired
    private TagDaoImpl(SessionFactory sessionFactory) {
        super(Tag.class, sessionFactory);
    }

    /**
     * Get one row from table by name
     * @param tagName tag mane
     * @return row object
     * @throws DaoException custom exception
     */
    public Tag getByName(String tagName) throws DaoException {
        try {
            Session session = currentSession();
            return (Tag) session.createCriteria(Tag.class)
                    .add(Restrictions.eq("name", tagName))
                    .uniqueResult();
        } catch (NonUniqueResultException e) {
            throw new DaoException(TagDaoImpl.class, "Duplicates detected it TAG table", e);
        } catch (HibernateException e) {
            throw new DaoException(TagDaoImpl.class, "Fatal error in getByName method", e);
        }
    }
}
