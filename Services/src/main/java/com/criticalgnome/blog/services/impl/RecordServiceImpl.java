package com.criticalgnome.blog.services.impl;

import com.criticalgnome.blog.constants.ServiceConstants;
import com.criticalgnome.blog.dao.IRecordDao;
import com.criticalgnome.blog.entities.Category;
import com.criticalgnome.blog.entities.Record;
import com.criticalgnome.blog.entities.Tag;
import com.criticalgnome.blog.entities.User;
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

    private final IRecordDao iRecordDao;

    @Autowired
    protected RecordServiceImpl(IRecordDao iRecordDao) {
        super(iRecordDao);
        this.iRecordDao = iRecordDao;
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Record> getRecordsByPage(int pageNumber, int pageCapacity, Category categoryScope, User userScope, Tag tagScope) throws ServiceException {
        int pageOffset = pageCapacity * pageNumber - pageCapacity;
        List<Record> records;
        try {
            records = iRecordDao.getRecordsByPage(pageOffset, pageCapacity, categoryScope, userScope, tagScope);
        } catch (DaoException e) {
            log.error(ServiceConstants.TRANSACTION_FAILED);
            throw new ServiceException(RecordServiceImpl.class, ServiceConstants.TRANSACTION_FAILED, e);
        }
        return records;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getRecordsCount(Category categoryScope, User userScope, Tag tagScope) throws ServiceException {
        int recordsCount;
        try {
            recordsCount = iRecordDao.getRecordsCount(categoryScope, userScope, tagScope);
        } catch (DaoException e) {
            log.error(ServiceConstants.TRANSACTION_FAILED);
            throw new ServiceException(RecordServiceImpl.class, ServiceConstants.TRANSACTION_FAILED, e);
        }
        return recordsCount;
    }
}
