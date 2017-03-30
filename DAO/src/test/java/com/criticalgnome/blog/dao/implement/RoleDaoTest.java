package com.criticalgnome.blog.dao.implement;

import com.criticalgnome.blog.entities.Role;
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
public class RoleDaoTest {

    private static Role expected;
    private static Role actual;

    @Test
    public void test1() throws Exception {
        expected = new Role(null, "Test role");
        Long id = RoleDao.getInstance().create(expected);
        actual = RoleDao.getInstance().getById(id);
        System.out.println(expected);
        System.out.println(actual);
        Assert.assertEquals("Not equal", expected, actual);
    }

    @Test
    public void test2() throws Exception {
        expected.setName("New name for role");
        RoleDao.getInstance().update(expected);
        actual = RoleDao.getInstance().getById(expected.getId());
        Assert.assertEquals("Not equal", expected, actual);
    }

    @Test
    public void test3() throws Exception {
        RoleDao.getInstance().remove(expected.getId());
        actual = RoleDao.getInstance().getById(expected.getId());
        Assert.assertNull("Not deleted", actual);
    }

}
