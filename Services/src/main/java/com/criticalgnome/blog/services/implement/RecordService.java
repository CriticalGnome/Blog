package com.criticalgnome.blog.services.implement;

import com.criticalgnome.blog.dao.implement.RecordDao;
import com.criticalgnome.blog.entities.Record;
import com.criticalgnome.blog.exceptions.DAOException;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.AbstractService;
import lombok.extern.log4j.Log4j2;

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
    public Long create(Record record) throws DAOException, ServiceException {
        return RecordDao.getInstance().create(record);
    }

    @Override
    public Record getById(Long id) throws DAOException, ServiceException {
        return RecordDao.getInstance().getById(id);
    }

    @Override
    public void update(Record record) throws DAOException, ServiceException {
        RecordDao.getInstance().update(record);
    }

    @Override
    public void remove(Long id) throws DAOException, ServiceException {
        RecordDao.getInstance().remove(id);
    }
}
