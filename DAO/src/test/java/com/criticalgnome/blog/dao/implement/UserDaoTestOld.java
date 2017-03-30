package com.criticalgnome.blog.dao.implement;

import com.criticalgnome.blog.entities.Role;
import com.criticalgnome.blog.entities.User;
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
public class UserDaoTestOld {

    private static User actual;
    private static User expected;

    @Test
    public void stage1_getInstanceTest() {
        UserDAOold instance1 = UserDAOold.getInstance();
        UserDAOold instance2 = UserDAOold.getInstance();
        Assert.assertEquals("Not singleton", instance1.hashCode(), instance2.hashCode());
    }

    @Test
    public void stage2_createAndGetByIdTest() throws Exception {
        int maxId = UserDAOold.getInstance().getMaxId();
        Role role = RoleDAOold.getInstance().getById(1);
        expected = new User(maxId + 1, "me@my.com", "qwerty", "Tester", "Test", "User", role);
        UserDAOold.getInstance().create(expected);
        actual = UserDAOold.getInstance().getById(expected.getId());
        Assert.assertEquals("Create and read test not valid", expected, actual);
    }

    @Test
    public void stage3_getAllTest() throws Exception {
        List<User> userList = UserDAOold.getInstance().getAll();
        Assert.assertTrue("GetAll test not valid", userList.size() > 0);
    }

    @Test
    public void stage4_updateTest() throws Exception {
        expected.setNickName("NewCoolTest");
        UserDAOold.getInstance().update(expected);
        actual = UserDAOold.getInstance().getById(expected.getId());
        Assert.assertEquals("Update test not valid", expected, actual);
    }

    @Test
    public void stage5_removeTest() throws Exception {
        UserDAOold.getInstance().remove(expected.getId());
        actual = UserDAOold.getInstance().getById(expected.getId());
        Assert.assertNull("Remove test not valid", actual);
    }
}
