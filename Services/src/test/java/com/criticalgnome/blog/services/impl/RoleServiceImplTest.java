package com.criticalgnome.blog.services.impl;

import com.criticalgnome.blog.entities.Role;
import com.criticalgnome.blog.services.IRoleService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Project Blog
 * Created on 13.04.2017.
 *
 * @author CriticalGnome
 */
@ContextConfiguration("/context-services-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class RoleServiceImplTest {

    @Autowired
    private
    IRoleService roleService;

    @Test
    public void roleServiceCreateTest() throws Exception {
        Role expected = new Role(null, "New role from Service");
        roleService.create(expected);
        Role actual = roleService.getById(expected.getId());
        Assert.assertEquals("Not equal:", expected, actual);
        roleService.remove(expected.getId());
    }

    @Test
    public void roleServiceUpdateTest() throws Exception {
        Role expected = new Role(null, "New role from Service");
        roleService.create(expected);
        expected.setName("New name for new role");
        roleService.update(expected);
        Role actual = roleService.getById(expected.getId());
        Assert.assertEquals("Not equal:", expected, actual);
        roleService.remove(expected.getId());
    }

    @Test
    public void roleServiceGetAllTest() throws Exception {
        int expected = roleService.getAll().size();
        Role role = new Role(null, "New role from Service");
        roleService.create(role);
        int actual = roleService.getAll().size();
        Assert.assertEquals("Not equal:", expected, actual - 1);
        roleService.remove(role.getId());
    }

    @Test
    public void roleServiceGetByNameTest() throws Exception {
        Role expected = new Role(null, "New role from Service");
        roleService.create(expected);
        Role actual = roleService.getByName(expected.getName());
        Assert.assertEquals("Not equal:", expected, actual);
        roleService.remove(expected.getId());
    }

}
