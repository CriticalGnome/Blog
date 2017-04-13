package com.criticalgnome.blog.services.impl;

import com.criticalgnome.blog.entities.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Project Blog
 * Created on 13.04.2017.
 *
 * @author CriticalGnome
 */
public class UserServiceImplTest {

    private UserServiceImpl userService = UserServiceImpl.getInstance();

    @Test
    public void testingCategoryService() throws Exception {
        User expected = new User(null, " ", " ", "Test Name", " ", " ", null);
        userService.create(expected);
        User actual = userService.getById(expected.getId());
        Assert.assertEquals("Not equals", expected, actual);
        expected.setNickName("New Nickname test user");
        userService.update(expected);
        actual = userService.getById(expected.getId());
        Assert.assertEquals("Not equals", expected, actual);
        List<User> categories = userService.getAll();
        Assert.assertTrue("No list", categories.size() > 0);
        userService.remove(expected.getId());
        actual = userService.getById(expected.getId());
        Assert.assertNull("Not deleted", actual);
    }

}
