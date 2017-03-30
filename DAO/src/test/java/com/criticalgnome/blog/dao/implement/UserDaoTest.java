package com.criticalgnome.blog.dao.implement;

import com.criticalgnome.blog.entities.Role;
import com.criticalgnome.blog.entities.User;
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
public class UserDaoTest {

    private static User expected;
    private static User actual;
    private static Role role;

    @Test
    public void test1() throws Exception {
        role = new Role(null, "Test role");
        RoleDao.getInstance().create(role);
        expected = new User(
                null,
                "me@my.com",
                "qwerty",
                "Sergey",
                "Kalinovsky",
                "CriticalGnome",
                role);
        UserDao.getInstance().create(expected);
        actual = UserDao.getInstance().getById(expected.getId());
        Assert.assertEquals("Not equal", expected, actual);
    }

    @Test
    public void test2() throws Exception {
        expected.setEmail("lord.skiminok@gmail.com");
        UserDao.getInstance().update(expected);
        actual = UserDao.getInstance().getById(expected.getId());
        Assert.assertEquals("Not equal", expected, actual);
    }

    @Test
    public void test3() throws Exception {
        UserDao.getInstance().remove(expected.getId());
        RoleDao.getInstance().remove(role.getId());
        actual = UserDao.getInstance().getById(expected.getId());
        Assert.assertNull("Not deleted", actual);
    }
}
