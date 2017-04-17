package com.criticalgnome.blog.dao.impl;

import com.criticalgnome.blog.dao.IRoleDao;
import com.criticalgnome.blog.dao.IUserDao;
import com.criticalgnome.blog.entities.Role;
import com.criticalgnome.blog.entities.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
@ContextConfiguration("/context-dao-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional(transactionManager = "transactionManager")
public class UserDaoImplTest {

    @Autowired
    private IUserDao userDao;
    @Autowired
    private IRoleDao roleDao;

    @Test
    public void userDaoCreateTest() throws Exception {
        Role role = new Role(null, "Test role");
        roleDao.create(role);
        User expected = new User(null,"me@my.com","qwerty","John","Doe","Dude",role);
        userDao.create(expected);
        User actual = userDao.getById(expected.getId());
        Assert.assertEquals("Not equal:", expected, actual);
        userDao.remove(expected.getId());
        roleDao.remove(role.getId());
    }

    @Test
    public void userDaoUpdateTest() throws Exception {
        Role role = new Role(null, "Test role");
        roleDao.create(role);
        User expected = new User(null,"me@my.com","qwerty","John","Doe","Dude",role);
        userDao.create(expected);
        expected.setNickName("Loser");
        userDao.update(expected);
        User actual = userDao.getById(expected.getId());
        Assert.assertEquals("Not equal:", expected, actual);
        userDao.remove(expected.getId());
        roleDao.remove(role.getId());
    }

    @Test
    public void userDaoGetAllTest() throws Exception {
        int expected = userDao.getAll().size();
        Role role = new Role(null, "Test role");
        roleDao.create(role);
        User user = new User(null,"me@my.com","qwerty","John","Doe","Dude",role);
        userDao.create(user);
        int actual = userDao.getAll().size();
        Assert.assertEquals("Not equal:", expected, actual - 1);
        userDao.remove(user.getId());
        roleDao.remove(role.getId());
    }

    @Test
    public void userDaoGetByEmailAndPasswordTest() throws Exception {
        Role role = new Role(null, "Test role");
        roleDao.create(role);
        User expected = new User(null,"me@my.com","qwerty","John","Doe","Dude",role);
        userDao.create(expected);
        User actual = userDao.getByEmailAndPassword("me@my.com", "qwerty");
        Assert.assertEquals("Not equal:", expected, actual);
        userDao.remove(expected.getId());
        roleDao.remove(role.getId());
    }

}
