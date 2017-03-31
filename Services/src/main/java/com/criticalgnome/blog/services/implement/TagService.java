package com.criticalgnome.blog.services.implement;

import com.criticalgnome.blog.dao.implement.TagDao;
import com.criticalgnome.blog.entities.Tag;
import com.criticalgnome.blog.exceptions.DaoException;
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
public class TagService extends AbstractService<Tag> {

    private static volatile TagService instance;

    private TagService() {}

    public static TagService getInstance() {
        if (instance == null) {
            synchronized (TagService.class) {
                if (instance == null) {
                    instance = new TagService();
                }
            }
        }
        return instance;
    }

    @Override
    public Long create(Tag tag) throws DaoException, ServiceException {
        return TagDao.getInstance().create(tag);
    }

    @Override
    public Tag getById(Long id) throws DaoException, ServiceException {
        return TagDao.getInstance().getById(id);
    }

    @Override
    public void update(Tag tag) throws DaoException, ServiceException {
        TagDao.getInstance().update(tag);
    }

    @Override
    public void remove(Long id) throws DaoException, ServiceException {
        TagDao.getInstance().remove(id);
    }

    public Tag getOrCreateTagByName(String tagName) throws DaoException, ServiceException {
        Tag tag = TagDao.getInstance().getByName(tagName);
        if (tag == null) {
            tag = new Tag(null, tagName);
            Long id = TagService.getInstance().create(tag);
        }
        return tag;
    }
}
