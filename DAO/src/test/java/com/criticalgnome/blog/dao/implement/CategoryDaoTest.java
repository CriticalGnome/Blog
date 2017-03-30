package com.criticalgnome.blog.dao.implement;

import com.criticalgnome.blog.entities.Category;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CategoryDaoTest {

    private static Category expected;
    private static Category actual;
    private static Category subcategory;

    @Test
    public void test1() throws Exception {
        expected = new Category(null, "New category", null);
        CategoryDao.getInstance().create(expected);
        subcategory = new Category(null, "New subcategory", expected);
        CategoryDao.getInstance().create(subcategory);
        actual = CategoryDao.getInstance().getById(expected.getId());
        Assert.assertEquals("Not equal", expected, actual);
    }

    @Test
    public void test2() throws Exception {
        expected.setName("New name for category");
        CategoryDao.getInstance().update(expected);
        actual = CategoryDao.getInstance().getById(expected.getId());
        Assert.assertEquals("Not equal", expected, actual);
    }

    @Test
    public void test3() throws Exception {
        CategoryDao.getInstance().remove(subcategory.getId());
        CategoryDao.getInstance().remove(expected.getId());
        actual = CategoryDao.getInstance().getById(expected.getId());
        Assert.assertNull("Not deleted", actual);
    }
}
