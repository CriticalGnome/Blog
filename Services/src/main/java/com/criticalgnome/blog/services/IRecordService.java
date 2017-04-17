package com.criticalgnome.blog.services;

import com.criticalgnome.blog.entities.Record;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.exceptions.ServiceException;

import java.util.List;

/**
 * Project Blog
 * Created on 13.04.2017.
 *
 * @author CriticalGnome
 */
public interface IRecordService extends IService<Record> {
    List<Record> getRecordsByPage(int pageNumber, int pageCapacity) throws ServiceException;
    int getRecordsCount() throws ServiceException;
}
