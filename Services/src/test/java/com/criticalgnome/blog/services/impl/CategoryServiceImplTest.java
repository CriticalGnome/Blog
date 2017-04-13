package com.criticalgnome.blog.services.impl;

import com.criticalgnome.blog.entities.Category;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Project Blog
 * Created on 13.04.2017.
 *
 * @author CriticalGnome
 */
public class CategoryServiceImplTest {

    private CategoryServiceImpl categoryService = CategoryServiceImpl.getInstance();

    @Test
    public void testingCategoryService() throws Exception {
        Category expected = new Category(null, "Test category", null);
        categoryService.create(expected);
        Category actual = categoryService.getById(expected.getId());
        Assert.assertEquals("Not equals", expected, actual);
        expected.setName("New name for test category");
        categoryService.update(expected);
        actual = categoryService.getById(expected.getId());
        Assert.assertEquals("Not equals", expected, actual);
        List<Category> categories = categoryService.getAll();
        Assert.assertTrue("No list", categories.size() > 0);
        categoryService.remove(expected.getId());
        actual = categoryService.getById(expected.getId());
        Assert.assertNull("Not deleted", actual);
    }

}
