package com.criticalgnome.blog.services;

import com.criticalgnome.blog.entities.Category;
import com.criticalgnome.blog.entities.Record;
import com.criticalgnome.blog.entities.Tag;
import com.criticalgnome.blog.entities.User;
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

    /**
     * Transmits main query to DAO layer
     *
     * @param pageNumber number of requested page
     * @param pageCapacity number of records on one page
     * @param categoryScope narrowing scope
     * @param userScope narrowing scope
     * @param tagScope narrowing scope
     * @return List of records
     * @throws ServiceException custom exception
     */
    List<Record> getRecordsByPage(int pageNumber, int pageCapacity, Category categoryScope, User userScope, Tag tagScope) throws ServiceException;

    /**
     * Request records count with narrowing scopes
     *
     * @param categoryScope narrowing scope
     * @param userScope narrowing scope
     * @param tagScope narrowing scope
     * @return records count
     * @throws ServiceException custom exception
     */
    int getRecordsCount(Category categoryScope, User userScope, Tag tagScope) throws ServiceException;

}
