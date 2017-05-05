package com.criticalgnome.blog.dao.impl;

import com.criticalgnome.blog.dao.IRoleDao;
import com.criticalgnome.blog.entities.Role;
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
public class RoleDaoImplTest {

    @Autowired
    private IRoleDao roleDao;

    @Test
    public void roleDaoCreateTest() throws Exception {
        Role expected = new Role(null, "New role");
        roleDao.create(expected);
        Role actual = roleDao.getById(expected.getId());
        Assert.assertEquals("Not equal:", expected, actual);
        roleDao.remove(expected.getId());
    }

    @Test
    public void roleDaoUpdateTest() throws Exception {
        Role expected = new Role(null, "New role");
        roleDao.create(expected);
        expected.setName("New name for new role");
        roleDao.update(expected);
        Role actual = roleDao.getById(expected.getId());
        Assert.assertEquals("Not equal:", expected, actual);
        roleDao.remove(expected.getId());
    }

    @Test
    public void roleDaoGetAllTest() throws Exception {
        int expected = roleDao.getAll().size();
        Role role = new Role(null, "New role");
        roleDao.create(role);
        int actual = roleDao.getAll().size();
        Assert.assertEquals("Not equal:", expected, actual - 1);
        roleDao.remove(role.getId());
    }

    @Test
    public void roleDaoGetbyName() throws Exception {
        Role expected = new Role(null, "New role");
        roleDao.create(expected);
        Role actual = roleDao.getByName(expected.getName());
        Assert.assertEquals("Not equal:", expected, actual);
        roleDao.remove(expected.getId());
    }


}
