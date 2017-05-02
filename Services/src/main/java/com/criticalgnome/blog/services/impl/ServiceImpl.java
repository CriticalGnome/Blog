package com.criticalgnome.blog.services.impl;

import com.criticalgnome.blog.constants.ServiceConstants;
import com.criticalgnome.blog.dao.IDao;
import com.criticalgnome.blog.entities.AbstractEntity;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.IService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Blog
 * Created on 22.03.2017.
 *
 * @author CriticalGnome
 */
@Service
@Log4j2
public abstract class ServiceImpl<T extends AbstractEntity> implements IService<T> {

    private IDao<T> iDao;

    @Autowired
    private TransactionTemplate transactionTemplate;

    protected ServiceImpl(IDao<T> iDao) {
        this.iDao = iDao;
    }

    /**
     * Create new entity instance
     *
     * @param abstractEntity generic entity
     * @return id
     * @throws ServiceException custom exception
     */
    @Override
    public Long create(final T abstractEntity) throws ServiceException {
        return transactionTemplate.execute(new TransactionCallback<Long>() {
            @Override
            public Long doInTransaction(TransactionStatus transactionStatus) {
                Long id = null;
                try {
                    id = iDao.create(abstractEntity);
                    log.info(ServiceConstants.TRANSACTION_SUCCEEDED);
                    log.info(abstractEntity);
                } catch (DaoException e) {
                    log.error(ServiceConstants.TRANSACTION_FAILED, e);
                    transactionStatus.setRollbackOnly();
                }
                return id;
            }
        });
    }

    /**
     * Return generic instance by id
     * @param id id
     * @return entity
     * @throws ServiceException custom exception
     */
    @Override
    public T getById(final Long id) throws ServiceException {
        return transactionTemplate.execute(new TransactionCallback<T>() {
            @Override
            public T doInTransaction(TransactionStatus transactionStatus) {
                T abstractEntity = null;
                try {
                    abstractEntity = iDao.getById(id);
                    log.info(ServiceConstants.TRANSACTION_SUCCEEDED);
                    log.info(abstractEntity);
                }
                catch (DaoException e) {
                    log.error(ServiceConstants.TRANSACTION_FAILED, e);
                    transactionStatus.setRollbackOnly();
                }
                return abstractEntity;
            }
        });
    }

    /**
     * Update generic entity
     *
     * @param abstractEntity entity instance
     * @throws ServiceException custom exception
     */
    @Override
    public void update(final T abstractEntity) throws ServiceException {
        transactionTemplate.execute(new TransactionCallback<Void>() {
            @Override
            public Void doInTransaction(TransactionStatus transactionStatus) {
                try {
                    iDao.update(abstractEntity);
                    log.info(ServiceConstants.TRANSACTION_SUCCEEDED);
                    log.info(abstractEntity);
                } catch (DaoException e) {
                    log.error(ServiceConstants.TRANSACTION_FAILED, e);
                    transactionStatus.setRollbackOnly();
                }
                return null;
            }
        });
    }

    /**
     * Delete entity instance
     *
     * @param id id
     * @throws ServiceException custom exception
     */
    @Override
    public void remove(final Long id) throws ServiceException {
        transactionTemplate.execute(new TransactionCallback<Void>() {
            @Override
            public Void doInTransaction(TransactionStatus transactionStatus){
                try {
                    iDao.remove(id);
                    log.info(ServiceConstants.TRANSACTION_SUCCEEDED);
                    log.info(ServiceConstants.MESSAGE_DELETE + id);
                }
                catch (DaoException e) {
                    log.error(ServiceConstants.TRANSACTION_FAILED, e);
                    transactionStatus.setRollbackOnly();
                }
                return null;
            }
        });
    }

    /**
     * List of all instances of generic entity
     *
     * @return List
     * @throws ServiceException custom exception
     */
    @Override
    public List<T> getAll() throws ServiceException {
        return transactionTemplate.execute(new TransactionCallback<List<T>>() {
            @Override
            public List<T> doInTransaction(TransactionStatus transactionStatus) {
                List<T> list = new ArrayList<>();
                try {
                    list = iDao.getAll();
                    log.info(ServiceConstants.TRANSACTION_SUCCEEDED);
                    log.info(list);
                }
                catch (DaoException e) {
                    log.error(ServiceConstants.TRANSACTION_FAILED, e);
                    transactionStatus.setRollbackOnly();
                }
                return list;
            }
        });
    }

}
