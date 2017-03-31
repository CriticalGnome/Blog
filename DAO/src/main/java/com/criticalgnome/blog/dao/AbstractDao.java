package com.criticalgnome.blog.dao;

import com.criticalgnome.blog.entities.Pojo;
import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
public abstract class AbstractDao<T extends Pojo> implements Dao<T> {
    protected Session session;
    protected Criteria criteria;
}
