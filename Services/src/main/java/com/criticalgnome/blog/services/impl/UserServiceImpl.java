package com.criticalgnome.blog.services.impl;

import com.criticalgnome.blog.constants.ServiceConstants;
import com.criticalgnome.blog.dao.IUserDao;
import com.criticalgnome.blog.entities.User;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.IUserService;
import com.criticalgnome.blog.utils.MD5;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = DaoException.class)
@Log4j2
public class UserServiceImpl extends ServiceImpl<User> implements IUserService {

    private final IUserDao iUserDao;

    @Autowired
    protected UserServiceImpl(IUserDao iUserDao) {
        super(iUserDao);
        this.iUserDao = iUserDao;
    }

    @Override
    public Long create(User user) throws ServiceException {
        Long id;
        user.setPassword(MD5.md5Encode(user.getPassword()));
        try {
            id = iUserDao.create(user);
            log.info(ServiceConstants.TRANSACTION_SUCCEEDED);
            log.info(user);
        } catch (DaoException e) {
            log.error(ServiceConstants.TRANSACTION_FAILED, e);
            throw new ServiceException(UserServiceImpl.class, ServiceConstants.TRANSACTION_FAILED, e);
        }
        return id;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public User getByEmailAndPassword(String email, String password) throws ServiceException {
        User user;
        try {
            user = iUserDao.getByEmailAndPassword(email, MD5.md5Encode(password));
        } catch (DaoException e) {
            log.error(ServiceConstants.TRANSACTION_FAILED);
            throw new ServiceException(UserServiceImpl.class, ServiceConstants.TRANSACTION_FAILED, e);
        }
        return user;
    }

    @Override
    public User getByEmail(String email) throws ServiceException {
        User user;
        try {
            user = iUserDao.getByEmail(email);
        } catch (DaoException e) {
            log.error(ServiceConstants.TRANSACTION_FAILED);
            throw new ServiceException(UserServiceImpl.class, ServiceConstants.TRANSACTION_FAILED, e);
        }
        return user;
    }

}
