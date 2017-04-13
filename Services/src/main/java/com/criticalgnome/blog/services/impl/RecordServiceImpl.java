package com.criticalgnome.blog.services.impl;

import com.criticalgnome.blog.dao.IRecordDao;
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
@Log4j2
public class RecordServiceImpl extends AbstractService<Record> implements IRecordService {

    private static volatile RecordServiceImpl instance;
    private static IRecordDao recordDao;

    private RecordServiceImpl(IRecordDao recordDao) { super(recordDao); }

    public static RecordServiceImpl getInstance() {
        if (instance == null) {
            synchronized (RecordServiceImpl.class) {
                if (instance == null) {
                    instance = new RecordServiceImpl(recordDao);
                }
            }
        }
        return instance;
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
