package com.criticalgnome.blog.services.impl;

import com.criticalgnome.blog.entities.Category;
import com.criticalgnome.blog.services.ICategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Project Blog
 * Created on 13.04.2017.
 *
 * @author CriticalGnome
 */
@ContextConfiguration("/context-services-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CategoryServiceImplTest {

    @Autowired
    private ICategoryService categoryService;

    @Test
    public void categoryServiceCreateTest() throws Exception {
        Category expected = new Category(null, "New category from Service", null);
        categoryService.create(expected);
        Category actual = categoryService.getById(expected.getId());
        Assert.assertEquals("Not equal:", expected, actual);
        categoryService.remove(expected.getId());
    }

    @Test
    public void categoryServiceUpdateTest() throws Exception {
        Category expected = new Category(null, "New category from Service", null);
        categoryService.create(expected);
        expected.setName("New name for new category");
        categoryService.update(expected);
        Category actual = categoryService.getById(expected.getId());
        Assert.assertEquals("Not equal:", expected, actual);
        categoryService.remove(expected.getId());
    }

    @Test
    public void categoryServiceGetAllTest() throws Exception {
        int expected = categoryService.getAll().size();
        Category category = new Category(null, "New category from Service", null);
        categoryService.create(category);
        int actual = categoryService.getAll().size();
        Assert.assertEquals("Not equal:", expected, actual - 1);
        categoryService.remove(category.getId());
    }

}
