package com.criticalgnome.blog.dao.impl;

import com.criticalgnome.blog.dao.IRoleDao;
import com.criticalgnome.blog.entities.Role;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
@Repository
public class RoleDaoImpl extends DaoImpl<Role> implements IRoleDao {

    @Autowired
    private RoleDaoImpl(SessionFactory sessionFactory) {
        super(Role.class, sessionFactory);
    }

}
