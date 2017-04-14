package com.criticalgnome.blog.dao.impl;

import com.criticalgnome.blog.dao.IRecordDao;
import com.criticalgnome.blog.entities.Record;
import com.criticalgnome.blog.exceptions.DaoException;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import java.util.List;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
public class RecordDaoImpl extends DaoImpl<Record> implements IRecordDao {

    private static volatile RecordDaoImpl instance;

    private RecordDaoImpl() {
        super(Record.class);
    }

    /**
     * Singleton pattern
     * @return dao instance
     */
    public static RecordDaoImpl getInstance() {
        if (instance == null) {
            synchronized (RecordDaoImpl.class) {
                if (instance == null) {
                    instance = new RecordDaoImpl();
                }
            }
        }
        return instance;
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
            Session session = util.getSession();
            Criteria criteria = session.createCriteria(Record.class);
            // Pagination
            criteria.setMaxResults(pageCapacity);
            criteria.setFirstResult(pageOffset);
            // Sort order
            criteria.addOrder(Order.desc("createdAt"));
            List<Record> records = (List<Record>) criteria.list();
            for (Record record : records) { int i = record.getTags().size(); }
            return records;
        } catch (HibernateException e) {
            throw new DaoException(RecordDaoImpl.class, "Fatal error in pagination method", e);
        }
    }

    /**
     * Get total count of all rows in table
     * @return count
     * @throws DaoException custom exception
     */
    public int getRecordsCount() throws DaoException {
        try{
            Session session = util.getSession();
            return Integer.parseInt(session.createCriteria(Record.class)
                    .setProjection(Projections.rowCount())
                    .uniqueResult().toString());
        } catch (HibernateException e) {
            throw new DaoException(RecordDaoImpl.class, "Fatal error in count method", e);
        }
    }
}
