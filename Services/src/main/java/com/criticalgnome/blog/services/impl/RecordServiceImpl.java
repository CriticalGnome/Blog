package com.criticalgnome.blog.services.impl;

import com.criticalgnome.blog.constants.ServiceConstants;
import com.criticalgnome.blog.dao.IRecordDao;
import com.criticalgnome.blog.entities.Record;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.IRecordService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = DaoException.class)
@Log4j2
public class RecordServiceImpl extends ServiceImpl<Record> implements IRecordService {

    @Autowired
    private IRecordDao iRecordDao;

    @Autowired
    protected RecordServiceImpl(IRecordDao iRecordDao) {
        super(iRecordDao);
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Record> getRecordsByPage(int pageNumber, int pageCapacity) throws ServiceException {
        int pageOffset = pageCapacity * pageNumber - pageCapacity;
        List<Record> records;
        try {
            records = iRecordDao.getRecordsByPage(pageOffset, pageCapacity);
        } catch (DaoException e) {
            log.error(ServiceConstants.TRANSACTION_FAILED);
            throw new ServiceException(RecordServiceImpl.class, ServiceConstants.TRANSACTION_FAILED, e);
        }
        if (records.size() == 0) {
            Record record = new Record(null, null, "No items", null, null, null, null, null);
            records.add(record);
        }
        return records;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getRecordsCount() throws ServiceException {
        int recordsCount;
        try {
            recordsCount = iRecordDao.getRecordsCount();
        } catch (DaoException e) {
            log.error(ServiceConstants.TRANSACTION_FAILED);
            throw new ServiceException(RecordServiceImpl.class, ServiceConstants.TRANSACTION_FAILED, e);
        }
        return recordsCount;
    }
}
