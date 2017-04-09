package com.criticalgnome.blog.services.implement;

import com.criticalgnome.blog.dao.implement.TagDao;
import com.criticalgnome.blog.entities.Tag;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.AbstractService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
@Log4j2
public class TagService extends AbstractService<Tag> {

    private static volatile TagService instance;
    private TagDao tagDao = TagDao.getInstance();

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
        Long id;
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            id = tagDao.create(tag);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(TagService.class, "Transaction failed in create method", e);
        }
        return id;
    }

    @Override
    public Tag getById(Long id) throws DaoException, ServiceException {
        Tag tag;
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            tag = tagDao.getById(id);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(TagService.class, "Transaction failed in getById method", e);
        }
        return tag;
    }

    @Override
    public void update(Tag tag) throws DaoException, ServiceException {
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            tagDao.update(tag);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(TagService.class, "Transaction failed in update method", e);
        }
    }

    @Override
    public void remove(Long id) throws DaoException, ServiceException {
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            tagDao.remove(id);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(TagService.class, "Transaction failed in remove method", e);
        }
    }

    public Tag getOrCreateTagByName(String tagName) throws DaoException, ServiceException {
        Tag tag = null;
        try {
            session = util.getSession();
            transaction = session.beginTransaction();
            tag = tagDao.getByName(tagName);
            transaction.commit();
            if (tag == null) {
                tagName = StringUtils.lowerCase(tagName);
                tagName = StringUtils.capitalize(tagName);
                tag = new Tag(null, tagName);
                try {
                    transaction = session.beginTransaction();
                    Long id = tagDao.create(tag);
                    transaction.commit();
                } catch (DaoException e) {
                    transaction.rollback();
                    throw new ServiceException(TagService.class, "Transaction failed in createTagByName method", e);
                }
            }
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(TagService.class, "Transaction failed in getOrCreateTagByName method", e);
        }
        return tag;
    }
}
