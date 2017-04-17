package com.criticalgnome.blog.dao.impl;

import com.criticalgnome.blog.dao.ICategoryDao;
import com.criticalgnome.blog.entities.Category;
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
public class CategoryDaoImplTest {

    @Autowired
    private ICategoryDao categoryDao;

    @Test
    public void categoryDaoCreateTest() throws Exception {
        Category expected = new Category(null, "New category", null);
        categoryDao.create(expected);
        Category actual = categoryDao.getById(expected.getId());
        Assert.assertEquals("Not equal:", expected, actual);
        categoryDao.remove(expected.getId());
    }

    @Test
    public void categoryDaoUpdateTest() throws Exception {
        Category expected = new Category(null, "New category", null);
        categoryDao.create(expected);
        expected.setName("New name for new category");
        categoryDao.update(expected);
        Category actual = categoryDao.getById(expected.getId());
        Assert.assertEquals("Not equal:", expected, actual);
        categoryDao.remove(expected.getId());
    }

    @Test
    public void categoryDaoGetAllTest() throws Exception {
        int expected = categoryDao.getAll().size();
        Category category = new Category(null, "New category", null);
        categoryDao.create(category);
        int actual = categoryDao.getAll().size();
        Assert.assertEquals("Not equal:", expected, actual - 1);
        categoryDao.remove(category.getId());
    }

}
