package com.criticalgnome.blog.dao.implement;

import com.criticalgnome.blog.entities.Role;
import com.criticalgnome.blog.entities.User;
import com.criticalgnome.blog.utils.EntityConstructor;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;

/**
 * Project Blog
 * Created on 21.03.2017.
 *
 * @author CriticalGnome
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDAOTest {

    private static User actual;
    private static User expected;

    @Test
    public void stage1_getInstanceTest() {
        UserDAO instance1 = UserDAO.getInstance();
        UserDAO instance2 = UserDAO.getInstance();
        Assert.assertEquals("Not singleton", instance1.hashCode(), instance2.hashCode());
    }

    @Test
    public void stage2_createAndGetByIdTest() throws Exception {
        int maxId = UserDAO.getInstance().getMaxId();
        Role role = RoleDAO.getInstance().getById(1);
        expected = EntityConstructor.buildUser(maxId + 1, "me@my.com", "qwerty", "Tester", "Test", "User", role);
        UserDAO.getInstance().create(expected);
        actual = UserDAO.getInstance().getById(expected.getId());
        Assert.assertEquals("Create and read test not valid", expected, actual);
    }

    @Test
    public void stage3_getAllTest() throws Exception {
        List<User> userList = UserDAO.getInstance().getAll();
        Assert.assertTrue("GetAll test not valid", userList.size() > 0);
    }

    @Test
    public void stage4_updateTest() throws Exception {
        expected.setNickName("NewCoolTest");
        UserDAO.getInstance().update(expected);
        actual = UserDAO.getInstance().getById(expected.getId());
        Assert.assertEquals("Update test not valid", expected, actual);
    }

    @Test
    public void stage5_removeTest() throws Exception {
        UserDAO.getInstance().remove(expected.getId());
        actual = UserDAO.getInstance().getById(expected.getId());
        Assert.assertNull("Remove test not valid", actual);
    }
}
