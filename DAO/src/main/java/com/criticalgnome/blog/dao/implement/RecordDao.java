package com.criticalgnome.blog.dao.implement;

import com.criticalgnome.blog.dao.AbstractDao;
import com.criticalgnome.blog.entities.Record;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.utils.HibernateConnector;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import java.util.List;

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

    /**
     * Singleton pattern
     * @return dao instance
     */
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

    /**
     * Create new row in Records table
     * @param record new object
     * @return id for created row
     * @throws DaoException custom exception
     */
    @Override
    public Long create(Record record) throws DaoException {
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
    public Record getById(Long id) throws DaoException {
        try {
            session = HibernateConnector.getInstance().getSession();
            session.beginTransaction();
            Record record = (Record) session.get(Record.class, id);
            int i = record.getTags().size();
            session.getTransaction().commit();
            return record;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            String message = "Fatal error in get record method";
            log.log(Level.ERROR, message);
            throw new DaoException(message, e);
        } finally {
            session.close();
        }
    }

    /**
     * Update object data in table
     * @param record object
     * @throws DaoException custom exception
     */
    @Override
    public void update(Record record) throws DaoException {
        try {
            session = HibernateConnector.getInstance().getSession();
            session.beginTransaction();
            session.update(record);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            String message = "Fatal error in update record method";
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
            Record record = getById(id);
            session = HibernateConnector.getInstance().getSession();
            session.beginTransaction();
            session.delete(record);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            String message = "Fatal error in remove record method";
            log.log(Level.ERROR, message);
            throw new DaoException(message, e);
        } finally {
            session.close();
        }
    }

    /**
     * Main page big query with parameters
     * @param pageOffset   row offset
     * @param pageCapacity row limit
     * @return list of records
     * @throws DaoException custom exception
     */
    public List<Record> getRecordsByPage(int pageOffset, int pageCapacity) throws DaoException {
        try {
            session = HibernateConnector.getInstance().getSession();
            session.beginTransaction();
            criteria = session.createCriteria(Record.class);
            // Pagination
            criteria.setMaxResults(pageCapacity);
            criteria.setFirstResult(pageOffset);
            // Sort order
            criteria.addOrder(Order.desc("createdAt"));
            List<Record> records = (List<Record>) criteria.list();
            for (Record record : records) { int i = record.getTags().size(); }
            session.getTransaction().commit();
            return records;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            String message = "Fatal error in pagination method";
            log.log(Level.ERROR, message, e);
            throw new DaoException(message, e);
        } finally {
            session.close();
        }
    }

    /**
     * get total count of all rows in table
     * @return count
     * @throws DaoException custom exception
     */
    public int getRecordsCount() throws DaoException {
        try{
            session = HibernateConnector.getInstance().getSession();
            return Integer.parseInt(session.createCriteria(Record.class)
                    .setProjection(Projections.rowCount())
                    .uniqueResult().toString());
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            String message = "Fatal error in count method";
            log.log(Level.ERROR, message);
            throw new DaoException(message, e);
        } finally {
            session.close();
        }
    }
}
