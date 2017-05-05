package com.criticalgnome.blog.services.impl;

import com.criticalgnome.blog.entities.Role;
import com.criticalgnome.blog.entities.User;
import com.criticalgnome.blog.services.IRoleService;
import com.criticalgnome.blog.services.IUserService;
import com.criticalgnome.blog.utils.MD5;
import org.junit.Assert;
import org.junit.Ignore;
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
public class UserServiceImplTest {

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @Test
    public void userServiceCreateTest() throws Exception {
        Role role = new Role(null, "Test role");
        roleService.create(role);
        User expected = new User(null,"me@my.com","qwerty","John","Doe","CoolDude",role);
        userService.create(expected);
        User actual = userService.getById(expected.getId());
        Assert.assertEquals("Not equal:", expected, actual);
        userService.remove(expected.getId());
        roleService.remove(role.getId());
    }

    @Test
    public void userServiceUpdateTest() throws Exception {
        Role role = new Role(null, "Test role");
        roleService.create(role);
        User expected = new User(null,"me@my.com","qwerty","John","Doe","CoolDude",role);
        userService.create(expected);
        expected.setNickName("New name for new user");
        userService.update(expected);
        User actual = userService.getById(expected.getId());
        Assert.assertEquals("Not equal:", expected, actual);
        userService.remove(expected.getId());
        roleService.remove(role.getId());
    }

    @Test
    public void userServiceGetAllTest() throws Exception {
        int expected = userService.getAll().size();
        Role role = new Role(null, "Test role");
        roleService.create(role);
        User user = new User(null,"me@my.com","qwerty","John","Doe","CoolDude",role);
        userService.create(user);
        int actual = userService.getAll().size();
        Assert.assertEquals("Not equal:", expected, actual - 1);
        userService.remove(user.getId());
        roleService.remove(role.getId());
    }

    @Test
    public void userServiceGetByEmailAndPasswordTest() throws Exception {
        Role role = new Role(null, "Test role");
        roleService.create(role);
        User user = new User(null,"me@my.com","qwerty","John","Doe","CoolDude",role);
        userService.create(user);
        User actual = userService.getByEmailAndPassword("me@my.com", "qwerty");
        Assert.assertNotNull("Not exist", actual);
        userService.remove(user.getId());
        roleService.remove(role.getId());
    }

    @Test
    public void userServiceGetByEmailTest() throws Exception {
        Role role = new Role(null, "Test role");
        roleService.create(role);
        User user = new User(null,"me@my.com","qwerty","John","Doe","CoolDude",role);
        userService.create(user);
        User actual = userService.getByEmail("me@my.com");
        Assert.assertNotNull("Not exist", actual);
        userService.remove(user.getId());
        roleService.remove(role.getId());
    }

}
