package com.criticalgnome.blog.dao.implement;

import com.criticalgnome.blog.dao.AbstractDao;
import com.criticalgnome.blog.entities.Record;
import com.criticalgnome.blog.entities.Tag;
import com.criticalgnome.blog.exceptions.DAOException;
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
public class RecordDao extends AbstractDao<Record> {

    private static volatile RecordDao instance;

    private RecordDao() {}

    public static RecordDao getInstance() {
        if (instance == null) {
            synchronized (RecordDao.class) {
                if (instance == null) {
                    instance = new RecordDao();
                }
            }
        }
        return instance;
    }

    @Override
    public Long create(Record record) throws DAOException {
        try {
            session = HibernateConnector.getInstance().getSession();
            session.beginTransaction();
            Long id = (Long) session.save(record);
            session.getTransaction().commit();
            log.log(Level.INFO, "New record created [{}] {}", record.getId(), record.getHeader());
            return id;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            String message = "Fatal error in create record method";
            log.log(Level.ERROR, message);
            throw new DAOException(message, e);
        } finally {
            session.close();
        }
    }

    @Override
    public Record getById(Long id) throws DAOException {
        try {
            session = HibernateConnector.getInstance().getSession();
            session.beginTransaction();
            Record record = (Record) session.get(Record.class, id);
            session.getTransaction().commit();
            Tag tag = null;
            if (record != null) {
                for (Tag t : record.getTags()) {
                    tag = t;
                    break;
                }
            }
            return record;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            String message = "Fatal error in get record method";
            log.log(Level.ERROR, message);
            throw new DAOException(message, e);
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Record record) throws DAOException {
        try {
            session = HibernateConnector.getInstance().getSession();
            session.beginTransaction();
            session.update(record);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            String message = "Fatal error in update record method";
            log.log(Level.ERROR, message);
            throw new DAOException(message, e);
        } finally {
            session.close();
        }
    }

    @Override
    public void remove(Long id) throws DAOException {
        try {
            Record record = getById(id);
            session = HibernateConnector.getInstance().getSession();
            session.beginTransaction();
            session.delete(record);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            String message = "Fatal error in remove record method";
            log.log(Level.ERROR, message);
            throw new DAOException(message, e);
        } finally {
            session.close();
        }
    }
}
