package com.criticalgnome.blog.services.impl;

import com.criticalgnome.blog.constants.ServiceConstants;
import com.criticalgnome.blog.dao.ITagDao;
import com.criticalgnome.blog.entities.Tag;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.ITagService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = DaoException.class)
@Log4j2
public class TagServiceImpl extends ServiceImpl<Tag> implements ITagService {

    private final ITagDao iTagDao;

    @Autowired
    protected TagServiceImpl(ITagDao iTagDao) {
        super(iTagDao);
        this.iTagDao = iTagDao;
    }

    public Tag getOrCreateTagByName(String tagName) throws ServiceException {
        Tag tag;
        try {
            tag = iTagDao.getByName(tagName);
            if (tag == null) {
                tagName = StringUtils.lowerCase(tagName);
                tagName = StringUtils.capitalize(tagName);
                tag = new Tag(null, tagName);
                try {
                    iTagDao.create(tag);
                } catch (DaoException e) {
                    log.error(ServiceConstants.TRANSACTION_FAILED);
                    throw new ServiceException(TagServiceImpl.class, ServiceConstants.TRANSACTION_FAILED, e);
                }
            }
        } catch (DaoException e) {
            log.error(ServiceConstants.TRANSACTION_FAILED);
            throw new ServiceException(TagServiceImpl.class, ServiceConstants.TRANSACTION_FAILED, e);
        }
        return tag;
    }
}
