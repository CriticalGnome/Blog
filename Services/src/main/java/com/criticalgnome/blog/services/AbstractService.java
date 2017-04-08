package com.criticalgnome.blog.services;

import com.criticalgnome.blog.entities.Pojo;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Project Blog
 * Created on 22.03.2017.
 *
 * @author CriticalGnome
 */
public abstract class AbstractService<T extends Pojo> implements Service<T> {
    protected Session session;
    protected Transaction transaction;
}
