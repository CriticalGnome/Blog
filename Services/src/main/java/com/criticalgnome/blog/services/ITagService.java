package com.criticalgnome.blog.services;

import com.criticalgnome.blog.entities.Tag;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.exceptions.ServiceException;

/**
 * Project Blog
 * Created on 13.04.2017.
 *
 * @author CriticalGnome
 */
public interface ITagService extends IService<Tag> {

    /**
     * Return tag by name or create if not exist
     *
     * @param tagName name
     * @return Tag
     * @throws ServiceException custom exception
     */
    Tag getOrCreateTagByName(String tagName) throws ServiceException;

}
