package com.criticalgnome.blog.services.impl;

import com.criticalgnome.blog.entities.Role;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Project Blog
 * Created on 13.04.2017.
 *
 * @author CriticalGnome
 */
public class RoleServiceImplTest {

    private RoleServiceImpl roleService = RoleServiceImpl.getInstance();

    @Test
    public void testingCategoryService() throws Exception {
        Role expected = new Role(null, "Test Role");
        roleService.create(expected);
        Role actual = roleService.getById(expected.getId());
        Assert.assertEquals("Not equals", expected, actual);
        expected.setName("New name for test role");
        roleService.update(expected);
        actual = roleService.getById(expected.getId());
        Assert.assertEquals("Not equals", expected, actual);
        List<Role> categories = roleService.getAll();
        Assert.assertTrue("No list", categories.size() > 0);
        roleService.remove(expected.getId());
        actual = roleService.getById(expected.getId());
        Assert.assertNull("Not deleted", actual);
    }

}
