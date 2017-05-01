package com.criticalgnome.blog.dao;

import com.criticalgnome.blog.entities.Category;
import com.criticalgnome.blog.entities.Record;
import com.criticalgnome.blog.entities.Tag;
import com.criticalgnome.blog.entities.User;
import com.criticalgnome.blog.exceptions.DaoException;

import java.util.List;

/**
 * Project Blog
 * Created on 13.04.2017.
 *
 * @author CriticalGnome
 */
public interface IRecordDao extends IDao<Record> {

    /**
     * Main page big query with parameters
     * @param pageOffset   row offset
     * @param pageCapacity row limit
     * @return list of records
     * @throws DaoException custom exception
     */
    List<Record> getRecordsByPage(int pageOffset, int pageCapacity, Category categoryScope, User userScope, Tag tagScope) throws DaoException;

    /**
     * Get total count of all rows in table
     * @return count
     * @throws DaoException custom exception
     */
    int getRecordsCount() throws DaoException;

}
