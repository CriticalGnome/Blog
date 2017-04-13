package com.criticalgnome.blog.dao.impl;

import com.criticalgnome.blog.entities.Role;
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
public class RoleDaoImplTest {

    private RoleDaoImpl roleDaoImpl = RoleDaoImpl.getInstance();

    @Test
    public void roleDaoTesting() throws Exception {

        // Begin
        Session session = HibernateUtil.getInstance().getSession();
        Transaction transaction;
        Role expected = new Role(null, "Test role");
        Role actual;

        // Create new role
        transaction = session.beginTransaction();
        Long id = roleDaoImpl.create(expected);
        actual = roleDaoImpl.getById(id);
        transaction.commit();
        Assert.assertEquals("Not equal", expected, actual);

        // Update role
        transaction = session.beginTransaction();
        expected.setName("New name for role");
        roleDaoImpl.update(expected);
        actual = roleDaoImpl.getById(expected.getId());
        transaction.commit();
        Assert.assertEquals("Not equal", expected, actual);

        //Remove Role
        transaction = session.beginTransaction();
        roleDaoImpl.remove(expected.getId());
        actual = roleDaoImpl.getById(expected.getId());
        transaction.commit();
        Assert.assertNull("Not deleted", actual);

        // End
        HibernateUtil.getInstance().releaseSession(session);
    }

}
