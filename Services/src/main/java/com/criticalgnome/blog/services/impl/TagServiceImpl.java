package com.criticalgnome.blog.services.impl;

import com.criticalgnome.blog.dao.impl.TagDaoImpl;
import com.criticalgnome.blog.entities.Tag;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.exceptions.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
public class TagServiceImpl extends ServiceImpl<Tag> {

    private static volatile TagServiceImpl instance;
    private TagDaoImpl tagDao = TagDaoImpl.getInstance();

    private TagServiceImpl() { super(); }

    public static TagServiceImpl getInstance() {
        if (instance == null) {
            synchronized (TagServiceImpl.class) {
                if (instance == null) {
                    instance = new TagServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public Long create(Tag tag) throws DaoException, ServiceException {
        Long id;
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            id = tagDao.create(tag);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(TagServiceImpl.class, "Transaction failed in create method", e);
        }
        return id;
    }

    @Override
    public Tag getById(Long id) throws DaoException, ServiceException {
        Tag abstractEntitty;
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            abstractEntitty = tagDao.getById(id);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(TagServiceImpl.class, "Transaction failed in getById method", e);
        }
        return abstractEntitty;
    }

    @Override
    public void update(Tag tag) throws DaoException, ServiceException {
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            tagDao.update(tag);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(TagServiceImpl.class, "Transaction failed in update method", e);
        }
    }

    @Override
    public void remove(Long id) throws DaoException, ServiceException {
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            tagDao.remove(id);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(TagServiceImpl.class, "Transaction failed in remove method", e);
        }
    }

    @Override
    public List<Tag> getAll() throws DaoException, ServiceException {
        List<Tag> abstractEntities;
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            abstractEntities = tagDao.getAll();
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(TagServiceImpl.class, "Transaction failed in getAll method", e);
        }
        return abstractEntities;
    }

    public Tag getOrCreateTagByName(String tagName) throws DaoException, ServiceException {
        Tag tag;
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            tag = TagDaoImpl.getInstance().getByName(tagName);
            transaction.commit();
            if (tag == null) {
                tagName = StringUtils.lowerCase(tagName);
                tagName = StringUtils.capitalize(tagName);
                tag = new Tag(null, tagName);
                try {
                    transaction = session.beginTransaction();
                    TagDaoImpl.getInstance().create(tag);
                    transaction.commit();
                } catch (DaoException e) {
                    transaction.rollback();
                    throw new ServiceException(TagServiceImpl.class, "Transaction failed in createTagByName method", e);
                }
            }
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(TagServiceImpl.class, "Transaction failed in getOrCreateTagByName method", e);
        }
        return tag;
    }
}
