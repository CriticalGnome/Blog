package com.criticalgnome.blog.dao.impl;

import com.criticalgnome.blog.dao.IRecordDao;
import com.criticalgnome.blog.entities.Category;
import com.criticalgnome.blog.entities.Record;
import com.criticalgnome.blog.entities.Tag;
import com.criticalgnome.blog.entities.User;
import com.criticalgnome.blog.exceptions.DaoException;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
@Repository
public class RecordDaoImpl extends DaoImpl<Record> implements IRecordDao {

    @Autowired
    private RecordDaoImpl(SessionFactory sessionFactory) {
        super(Record.class, sessionFactory);
    }

    /**
     * Main page big query with parameters
     * @param pageOffset   row offset
     * @param pageCapacity row limit
     * @return list of records
     * @throws DaoException custom exception
     */
    public List<Record> getRecordsByPage(int pageOffset, int pageCapacity, Category categoryScope, User userScope, Tag tagScope) throws DaoException {
        try {
            Session session = currentSession();
            Criteria criteria = session.createCriteria(Record.class, "r");
            // Pagination
            criteria.setMaxResults(pageCapacity);
            criteria.setFirstResult(pageOffset);
            // Narrow to categoryScope
            if (categoryScope != null) {
                criteria.createAlias("r.category", "c");
                criteria.add(Restrictions.eq("c.id", categoryScope.getId()));
            }
            // Narrow to userScope
            if (userScope != null) {
                criteria.createAlias("r.author", "u");
                criteria.add(Restrictions.eq("u.id", userScope.getId()));
            }
            // Narrow to tagScope
            if (tagScope != null) {
                criteria.createAlias("r.tags", "t");
                criteria.add(Restrictions.eq("t.id", tagScope.getId()));
            }
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
    public int getRecordsCount(Category categoryScope, User userScope, Tag tagScope) throws DaoException {
        try{
            Session session = currentSession();
            Criteria criteria = session.createCriteria(Record.class, "r");
            // Narrow to categoryScope
            if (categoryScope != null) {
                criteria.createAlias("r.category", "c");
                criteria.add(Restrictions.eq("c.id", categoryScope.getId()));
            }
            // Narrow to userScope
            if (userScope != null) {
                criteria.createAlias("r.author", "u");
                criteria.add(Restrictions.eq("u.id", userScope.getId()));
            }
            // Narrow to tagScope
            if (tagScope != null) {
                criteria.createAlias("r.tags", "t");
                criteria.add(Restrictions.eq("t.id", tagScope.getId()));
            }
            return Integer.parseInt(criteria.setProjection(Projections.rowCount()).uniqueResult().toString());
        } catch (HibernateException e) {
            throw new DaoException(RecordDaoImpl.class, "Fatal error in count method", e);
        }
    }
}
