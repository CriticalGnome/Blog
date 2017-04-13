package com.criticalgnome.blog.dao.impl;

import com.criticalgnome.blog.dao.AbstractDao;
import com.criticalgnome.blog.dao.ITagDao;
import com.criticalgnome.blog.entities.Tag;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.utils.HibernateUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.hibernate.HibernateException;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
@Log4j2
public class TagDaoImpl extends AbstractDao<Tag> implements ITagDao {

    private static volatile TagDaoImpl instance;

    private TagDaoImpl() {
        super(Tag.class);
    }

    /**
     * Singleton pattern
     * @return dao instance
     */
    public static TagDaoImpl getInstance() {
        if (instance == null) {
            synchronized (TagDaoImpl.class) {
                if (instance == null) {
                    instance = new TagDaoImpl();
                }
            }
        }
        return instance;
    }

    /**
     * Get one row from table by name
     * @param tagName tag mane
     * @return row object
     * @throws DaoException custom exception
     */
    public Tag getByName(String tagName) throws DaoException {
        try {
            Session session = HibernateUtil.getInstance().getSession();
            return (Tag) session.createCriteria(Tag.class)
                    .add(Restrictions.eq("name", tagName))
                    .uniqueResult();
        } catch (NonUniqueResultException e) {
            String message = "Duplicates detected it TAG table";
            log.log(Level.ERROR, message);
            throw new DaoException(message, e);
        } catch (HibernateException e) {
            throw new DaoException(TagDaoImpl.class, "Fatal error in getByName method", e);
        }
    }
}
