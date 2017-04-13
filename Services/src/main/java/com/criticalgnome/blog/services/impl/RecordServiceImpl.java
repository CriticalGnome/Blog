package com.criticalgnome.blog.services.impl;

import com.criticalgnome.blog.dao.impl.RecordDaoImpl;
import com.criticalgnome.blog.entities.Record;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.AbstractService;
import com.criticalgnome.blog.services.IRecordService;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
public class RecordServiceImpl extends AbstractService<Record> implements IRecordService {

    private static volatile RecordServiceImpl instance;
    private RecordDaoImpl recordDao = RecordDaoImpl.getInstance();

    private RecordServiceImpl() { super(); }

    public static RecordServiceImpl getInstance() {
        if (instance == null) {
            synchronized (RecordServiceImpl.class) {
                if (instance == null) {
                    instance = new RecordServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public Long create(Record record) throws DaoException, ServiceException {
        Long id;
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            id = recordDao.create(record);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(RecordServiceImpl.class, "Transaction failed in create method", e);
        }
        return id;
    }

    @Override
    public Record getById(Long id) throws DaoException, ServiceException {
        Record abstractEntitty;
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            abstractEntitty = recordDao.getById(id);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(RecordServiceImpl.class, "Transaction failed in getById method", e);
        }
        return abstractEntitty;
    }

    @Override
    public void update(Record record) throws DaoException, ServiceException {
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            recordDao.update(record);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(RecordServiceImpl.class, "Transaction failed in update method", e);
        }
    }

    @Override
    public void remove(Long id) throws DaoException, ServiceException {
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            recordDao.remove(id);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(RecordServiceImpl.class, "Transaction failed in remove method", e);
        }
    }

    @Override
    public List<Record> getAll() throws DaoException, ServiceException {
        List<Record> abstractEntities;
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            abstractEntities = recordDao.getAll();
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(RecordServiceImpl.class, "Transaction failed in getAll method", e);
        }
        return abstractEntities;
    }

    public List<Record> getRecordsByPage(int pageNumber, int pageCapacity) throws DaoException, ServiceException {
        int pageOffset = pageCapacity * pageNumber - pageCapacity;
        List<Record> records;
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            records = RecordDaoImpl.getInstance().getRecordsByPage(pageOffset, pageCapacity);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(RecordServiceImpl.class, "Transaction failed in getRecordsByPage method", e);
        }
        if (records.size() == 0) {
            Record record = new Record(null, null, "No items", null, null, null, null, null);
            records.add(record);
        }
        return records;
    }

    public int getRecordsCount() throws DaoException, ServiceException {
        int recordsCount;
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            recordsCount = RecordDaoImpl.getInstance().getRecordsCount();
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(RecordServiceImpl.class, "Transaction failed in getRecordsCount method", e);
        }
        return recordsCount;
    }
}
