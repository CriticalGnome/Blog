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
     *
     * @param pageOffset    row offset
     * @param pageCapacity  row limit
     * @param categoryScope narrowing scope
     * @param userScope     narrowing scope
     * @param tagScope      narrowing scope
     * @return list of records
     * @throws DaoException custom exception
     */
    List<Record> getRecordsByPage(int pageOffset, int pageCapacity, Category categoryScope, User userScope, Tag tagScope) throws DaoException;

    /**
     * Return count of Records with considering to narrowing scope
     *
     * @param categoryScope narrowing scope
     * @param userScope     narrowing scope
     * @param tagScope      narrowing scope
     * @return count
     * @throws DaoException custom exception
     */
    int getRecordsCount(Category categoryScope, User userScope, Tag tagScope) throws DaoException;

}
