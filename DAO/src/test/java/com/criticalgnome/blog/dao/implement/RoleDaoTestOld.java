package com.criticalgnome.blog.dao.implement;

import com.criticalgnome.blog.entities.Role;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.util.List;

/**
 * Project Blog
 * Created on 21.03.2017.
 *
 * @author CriticalGnome
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoleDaoTestOld {

    private static Role actual;
    private static Role expected;

    @Test
    public void stage1_getInstanceTest() {
        RoleDAOold instance1 = RoleDAOold.getInstance();
        RoleDAOold instance2 = RoleDAOold.getInstance();
        Assert.assertEquals("Not singleton", instance1.hashCode(), instance2.hashCode());
    }

    @Test
    public void stage2_createAndGetByIdTest() throws Exception {
        int maxId = RoleDAOold.getInstance().getMaxId();
        expected = new Role(maxId + 1, "Test Role");
        RoleDAOold.getInstance().create(expected);
        actual = RoleDAOold.getInstance().getById(expected.getId());
        Assert.assertEquals("Create and read test not valid", expected, actual);
    }

    @Test
    public void stage3_getAllTest() throws Exception {
        List<Role> roleList = RoleDAOold.getInstance().getAll();
        Assert.assertTrue("GetAll test not valid", roleList.size() > 0);
    }

    @Test
    public void stage4_updateTest() throws Exception {
        expected.setName("Test2");
        RoleDAOold.getInstance().update(expected);
        actual = RoleDAOold.getInstance().getById(expected.getId());
        Assert.assertEquals("Update test not valid", expected, actual);
    }

    @Test
    public void stage5_removeTest() throws Exception {
        RoleDAOold.getInstance().remove(expected.getId());
        actual = RoleDAOold.getInstance().getById(expected.getId());
        Assert.assertNull("Remove test not valid", actual);
    }
}
