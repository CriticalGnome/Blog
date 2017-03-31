package com.criticalgnome.blog.services.implement;

import com.criticalgnome.blog.dao.implement.RecordDao;
import com.criticalgnome.blog.entities.Record;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.AbstractService;
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
        return RecordDao.getInstance().create(record);
    }

    @Override
    public Record getById(Long id) throws DaoException, ServiceException {
        return RecordDao.getInstance().getById(id);
    }

    @Override
    public void update(Record record) throws DaoException, ServiceException {
        RecordDao.getInstance().update(record);
    }

    @Override
    public void remove(Long id) throws DaoException, ServiceException {
        RecordDao.getInstance().remove(id);
    }

    public List<Record> getRecordsByPage(int pageNumber, int pageCapacity) throws DaoException, ServiceException {
        List<Record> records = RecordDao.getInstance().getRecordsByPage(pageNumber, pageCapacity);
        if (records.size() == 0) {
            Record record = new Record(null, null, "No items", null, null, null, null, null);
            records.add(record);
        }
        return records;
    }

    public int getRecordsCount() throws DaoException, ServiceException {
        return RecordDao.getInstance().getRecordsCount();
    }
}
