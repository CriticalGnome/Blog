package com.criticalgnome.blog.dao;

import com.criticalgnome.blog.entities.Tag;
import com.criticalgnome.blog.exceptions.DaoException;

/**
 * Project Blog
 * Created on 13.04.2017.
 *
 * @author CriticalGnome
 */
public interface ITagDao extends IDao<Tag> {

    /**
     * Get one row from table by name
     *
     * @param tagName tag mane
     * @return row object
     * @throws DaoException custom exception
     */
    public Tag getByName(String tagName) throws DaoException;

}