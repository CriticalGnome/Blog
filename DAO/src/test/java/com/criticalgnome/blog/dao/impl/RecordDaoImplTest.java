package com.criticalgnome.blog.dao.impl;

import com.criticalgnome.blog.entities.*;
import com.criticalgnome.blog.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
public class RecordDaoImplTest {

    private RecordDaoImpl recordDaoImpl = RecordDaoImpl.getInstance();

    @Test
    public void recordDaoTesting() throws Exception {

        // Begin
        Session session = HibernateUtil.getInstance().getSession();
        Transaction transaction;
        Set<Tag> tags = new HashSet<>();
        Record expected = new Record(null,"Header","Body text",null,null,null,null,tags);
        Record actual;

        // Create new record
        transaction = session.beginTransaction();
        recordDaoImpl.create(expected);
        actual = recordDaoImpl.getById(expected.getId());
        transaction.commit();
        Assert.assertEquals("Not equal", expected, actual);

        // Update record
        expected.setHeader("New Header");
        expected.setBody("New body text");
        transaction = session.beginTransaction();
        recordDaoImpl.update(expected);
        actual = recordDaoImpl.getById(expected.getId());
        transaction.commit();
        Assert.assertEquals("Not equal", expected, actual);

        // Remove record
        transaction = session.beginTransaction();
        recordDaoImpl.remove(expected.getId());
        actual = recordDaoImpl.getById(expected.getId());
        transaction.commit();
        Assert.assertNull("Not deleted", actual);

        // End
        HibernateUtil.getInstance().releaseSession(session);
    }
}
