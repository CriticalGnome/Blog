package com.criticalgnome.blog.dao.impl;

import com.criticalgnome.blog.entities.Tag;
import com.criticalgnome.blog.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
public class TagDaoImplTest {

    private TagDaoImpl tagDaoImpl = TagDaoImpl.getInstance();

    @Test
    public void tagDaoTesting() throws Exception {

    // Begin
        Session session = HibernateUtil.getInstance().getSession();
        Transaction transaction;
        Tag expected = new Tag(null, "Test tag");
        Tag actual;

        // Create new tag
        transaction = session.beginTransaction();
        Long id = tagDaoImpl.create(expected);
        actual = tagDaoImpl.getById(id);
        transaction.commit();
        Assert.assertEquals("Not equal", expected, actual);

        // Update tag
        expected.setName("New name for tag");
        transaction = session.beginTransaction();
        tagDaoImpl.update(expected);
        actual = tagDaoImpl.getById(expected.getId());
        transaction.commit();
        Assert.assertEquals("Not equal", expected, actual);

        // Remove tag
        transaction = session.beginTransaction();
        tagDaoImpl.remove(expected.getId());
        actual = tagDaoImpl.getById(expected.getId());
        transaction.commit();
        Assert.assertNull("Not deleted", actual);

        // End
        HibernateUtil.getInstance().releaseSession(session);
    }

}
