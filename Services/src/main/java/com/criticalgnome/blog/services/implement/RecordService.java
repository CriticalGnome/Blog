package com.criticalgnome.blog.services.implement;

import com.criticalgnome.blog.dao.implement.RecordDao;
import com.criticalgnome.blog.entities.Record;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.AbstractService;
import com.criticalgnome.blog.utils.HibernateUtil;
import lombok.extern.log4j.Log4j2;

import java.util.List;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
@Log4j2
public class RecordService extends AbstractService<Record> {

    private static volatile RecordService instance;
    private RecordDao recordDao = RecordDao.getInstance();

    private RecordService() {}

    public static RecordService getInstance() {
        if (instance == null) {
            synchronized (RecordService.class) {
                if (instance == null) {
                    instance = new RecordService();
                }
            }
        }
        return instance;
    }

    @Override
    public Long create(Record record) throws DaoException, ServiceException {
        Long id;
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            id = recordDao.create(record);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(RecordService.class, "Transaction failed in create method", e);
        }
        return id;
    }

    @Override
    public Record getById(Long id) throws DaoException, ServiceException {
        Record record;
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            record = recordDao.getById(id);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(RecordService.class, "Transaction failed in getBtId method", e);
        }
        return record;
    }

    @Override
    public void update(Record record) throws DaoException, ServiceException {
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            recordDao.update(record);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(RecordService.class, "Transaction failed in update method", e);
        }
    }

    @Override
    public void remove(Long id) throws DaoException, ServiceException {
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            recordDao.remove(id);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(RecordService.class, "Transaction failed in remove method", e);
        }
    }

    public List<Record> getRecordsByPage(int pageNumber, int pageCapacity) throws DaoException, ServiceException {
        int pageOffset = pageCapacity * pageNumber - pageCapacity;
        List<Record> records;
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            records = recordDao.getRecordsByPage(pageOffset, pageCapacity);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(RecordService.class, "Transaction failed in getRecordsByPage method", e);
        }
        if (records.size() == 0) {
            Record record = new Record(null, null, "No items", null, null, null, null, null);
            records.add(record);
        }
        return records;
    }

    public int getRecordsCount() throws DaoException, ServiceException {
        int recordsCount;
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            recordsCount = recordDao.getRecordsCount();
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(RecordService.class, "Transaction failed in getRecordsCount method", e);
        }
        return recordsCount;
    }
}
