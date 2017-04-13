package com.criticalgnome.blog.dao.impl;

import com.criticalgnome.blog.entities.Role;
import com.criticalgnome.blog.entities.User;
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoImplTest {

    private RoleDaoImpl roleDaoImpl = RoleDaoImpl.getInstance();
    private UserDaoImpl userDaoImpl = UserDaoImpl.getInstance();

    @Test
    public void userDaoTesting() throws Exception {

        // Begin
        Session session = HibernateUtil.getInstance().getSession();
        Transaction transaction;
        Role role = new Role(null, "Test role");
        User expected = new User(null,"me@my.com","qwerty","John","Doe","Dude",role);
        User actual;

        // Create new user
        transaction = session.beginTransaction();
        roleDaoImpl.create(role);
        userDaoImpl.create(expected);
        actual = userDaoImpl.getById(expected.getId());
        transaction.commit();
        Assert.assertEquals("Not equal", expected, actual);

        // Update User
        expected.setEmail("bill.gates@microsoft.com");
        expected.setFirstName("Bill");
        expected.setLastName("Gates");
        transaction = session.beginTransaction();
        userDaoImpl.update(expected);
        actual = userDaoImpl.getById(expected.getId());
        transaction.commit();
        Assert.assertEquals("Not equal", expected, actual);

        // Remove record
        transaction = session.beginTransaction();
        userDaoImpl.remove(expected.getId());
        roleDaoImpl.remove(role.getId());
        actual = userDaoImpl.getById(expected.getId());
        transaction.commit();
        Assert.assertNull("Not deleted", actual);

        // End
        HibernateUtil.getInstance().releaseSession(session);
    }
}
