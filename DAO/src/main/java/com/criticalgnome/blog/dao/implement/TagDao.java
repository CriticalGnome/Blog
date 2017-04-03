package com.criticalgnome.blog.dao.implement;

import com.criticalgnome.blog.dao.AbstractDao;
import com.criticalgnome.blog.entities.Tag;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.utils.HibernateConnector;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.hibernate.HibernateException;
import org.hibernate.NonUniqueResultException;
import org.hibernate.criterion.Restrictions;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
@Log4j2
public class TagDao extends AbstractDao<Tag> {

    private static volatile TagDao instance;

    private TagDao() {}

    /**
     * Singleton pattern
     * @return dao instance
     */
    public static TagDao getInstance() {
        if (instance == null) {
            synchronized (TagDao.class) {
                if (instance == null) {
                    instance = new TagDao();
                }
            }
        }
        return instance;
    }

    /**
     * Create new row in table
     * @param tag object
     * @return id for created row
     * @throws DaoException custom exception
     */
    @Override
    public Long create(Tag tag) throws DaoException {
        try {
            session = HibernateConnector.getInstance().getSession();
            session.beginTransaction();
            Long id = (Long) session.save(tag);
            session.getTransaction().commit();
            log.log(Level.INFO, "New tag created [{}] {}", tag.getId(), tag.getName());
            return id;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            String message = "Fatal error in create tag method";
            log.log(Level.ERROR, message);
            throw new DaoException(message, e);
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
    public Tag getById(Long id) throws DaoException {
        try {
            session = HibernateConnector.getInstance().getSession();
            session.beginTransaction();
            Tag tag = (Tag) session.get(Tag.class, id);
            session.getTransaction().commit();
            return tag;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            String message = "Fatal error in get tag method";
            log.log(Level.ERROR, message);
            throw new DaoException(message, e);
        } finally {
            session.close();
        }

    }

    /**
     * Update object data in table
     * @param tag object
     * @throws DaoException custom exception
     */
    @Override
    public void update(Tag tag) throws DaoException {
        try {
            session = HibernateConnector.getInstance().getSession();
            session.beginTransaction();
            session.update(tag);
            session.getTransaction().commit();
            log.log(Level.INFO, "Tag updated [{}] {}", tag.getId(), tag.getName());
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            String message = "Fatal error in update tag method";
            log.log(Level.ERROR, message);
            throw new DaoException(message, e);
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
            Tag tag = getById(id);
            session = HibernateConnector.getInstance().getSession();
            session.beginTransaction();
            session.delete(tag);
            session.getTransaction().commit();
            log.log(Level.INFO, "Tag removed [{}] {}", tag.getId(), tag.getName());
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            String message = "Fatal error in remove tag method";
            log.log(Level.ERROR, message);
            throw new DaoException(message, e);
        } finally {
            session.close();
        }
    }

    /**
     * Get one row from table by name
     * @param tagName tag mane
     * @return row object
     * @throws DaoException custom exception
     */
    public Tag getByName(String tagName) throws DaoException {
        try {
            session = HibernateConnector.getInstance().getSession();
            return (Tag) session.createCriteria(Tag.class)
                    .add(Restrictions.like("name", tagName))
                    .uniqueResult();
        } catch (NonUniqueResultException e) {
            String message = "Duplicates detected it TAG table";
            log.log(Level.ERROR, message);
            throw new DaoException(message, e);
        } catch (HibernateException e) {
            String message = "Fatal error in getByName method";
            log.log(Level.ERROR, message);
            throw new DaoException(message, e);
        } finally {
            session.close();
        }
    }
}
