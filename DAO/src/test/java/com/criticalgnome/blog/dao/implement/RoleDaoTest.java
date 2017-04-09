package com.criticalgnome.blog.dao.implement;

import com.criticalgnome.blog.entities.Role;
import com.criticalgnome.blog.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
public class RoleDaoTest {

    private RoleDao roleDao = RoleDao.getInstance();

    @Test
    public void roleDaoTesting() throws Exception {

        // Begin
        Session session = HibernateUtil.getInstance().getSession();
        Transaction transaction;
        Role expected = new Role(null, "Test role");
        Role actual;

        // Create new role
        transaction = session.beginTransaction();
        Long id = roleDao.create(expected);
        actual = roleDao.getById(id);
        transaction.commit();
        Assert.assertEquals("Not equal", expected, actual);

        // Update role
        transaction = session.beginTransaction();
        expected.setName("New name for role");
        roleDao.update(expected);
        actual = roleDao.getById(expected.getId());
        transaction.commit();
        Assert.assertEquals("Not equal", expected, actual);

        //Remove Role
        transaction = session.beginTransaction();
        roleDao.remove(expected.getId());
        actual = roleDao.getById(expected.getId());
        transaction.commit();
        Assert.assertNull("Not deleted", actual);

        // End
        HibernateUtil.getInstance().releaseSession(session);
    }

}
