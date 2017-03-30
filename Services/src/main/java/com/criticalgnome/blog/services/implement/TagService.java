package com.criticalgnome.blog.services.implement;

import com.criticalgnome.blog.dao.implement.TagDao;
import com.criticalgnome.blog.entities.Tag;
import com.criticalgnome.blog.exceptions.DAOException;
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
    public Long create(Tag tag) throws DAOException, ServiceException {
        return TagDao.getInstance().create(tag);
    }

    @Override
    public Tag getById(Long id) throws DAOException, ServiceException {
        return TagDao.getInstance().getById(id);
    }

    @Override
    public void update(Tag tag) throws DAOException, ServiceException {
        TagDao.getInstance().update(tag);
    }

    @Override
    public void remove(Long id) throws DAOException, ServiceException {
        TagDao.getInstance().remove(id);
    }
}
