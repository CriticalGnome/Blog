package com.criticalgnome.blog.services;

import com.criticalgnome.blog.entities.AbstractEntity;
import com.criticalgnome.blog.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Project Blog
 * Created on 22.03.2017.
 *
 * @author CriticalGnome
 */
public abstract class AbstractService<T extends AbstractEntity> implements Service<T> {

    protected HibernateUtil util = HibernateUtil.getInstance();
    protected Session session;
    protected Transaction transaction;

}
