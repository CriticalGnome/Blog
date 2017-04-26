package com.criticalgnome.blog.services.impl;

import com.criticalgnome.blog.constants.ServiceConstants;
import com.criticalgnome.blog.dao.IRoleDao;
import com.criticalgnome.blog.entities.Role;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.IRoleService;
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
@Log4j2
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = DaoException.class)
public class RoleServiceImpl extends ServiceImpl<Role> implements IRoleService {

    private final IRoleDao iRoleDao;

    @Autowired
    protected RoleServiceImpl(IRoleDao iRoleDao) {
        super(iRoleDao);
        this.iRoleDao = iRoleDao;
    }

    public Role getByName(String roleName) throws ServiceException {
        Role role;
        try {
            role = iRoleDao.getByName(roleName);
        } catch (DaoException e) {
            log.error(ServiceConstants.TRANSACTION_FAILED);
            throw new ServiceException(RoleServiceImpl.class, ServiceConstants.TRANSACTION_FAILED, e);
        }
        return role;
    }
}
