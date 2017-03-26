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
public class RoleDAOTest {

    private static Role actual;
    private static Role expected;

    @Test
    public void stage1_getInstanceTest() {
        RoleDAO instance1 = RoleDAO.getInstance();
        RoleDAO instance2 = RoleDAO.getInstance();
        Assert.assertEquals("Not singleton", instance1.hashCode(), instance2.hashCode());
    }

    @Test
    public void stage2_createAndGetByIdTest() throws Exception {
        int maxId = RoleDAO.getInstance().getMaxId();
        expected = new Role(maxId + 1, "Test Role");
        RoleDAO.getInstance().create(expected);
        actual = RoleDAO.getInstance().getById(expected.getId());
        Assert.assertEquals("Create and read test not valid", expected, actual);
    }

    @Test
    public void stage3_getAllTest() throws Exception {
        List<Role> roleList = RoleDAO.getInstance().getAll();
        Assert.assertTrue("GetAll test not valid", roleList.size() > 0);
    }

    @Test
    public void stage4_updateTest() throws Exception {
        expected.setName("Test2");
        RoleDAO.getInstance().update(expected);
        actual = RoleDAO.getInstance().getById(expected.getId());
        Assert.assertEquals("Update test not valid", expected, actual);
    }

    @Test
    public void stage5_removeTest() throws Exception {
        RoleDAO.getInstance().remove(expected.getId());
        actual = RoleDAO.getInstance().getById(expected.getId());
        Assert.assertNull("Remove test not valid", actual);
    }
}
