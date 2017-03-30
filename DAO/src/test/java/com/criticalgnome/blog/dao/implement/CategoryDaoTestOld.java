package com.criticalgnome.blog.dao.implement;

import com.criticalgnome.blog.entities.Category;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.util.List;

/**
 * Project Blog
 * Created on 20.03.2017.
 *
 * @author CriticalGnome
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CategoryDaoTestOld {

    private static Category actual;
    private static Category expected;

    @Test
    public void stage1_getInstanceTest() {
        CategoryDAOold instance1 = CategoryDAOold.getInstance();
        CategoryDAOold instance2 = CategoryDAOold.getInstance();
        Assert.assertEquals("Not singleton", instance1.hashCode(), instance2.hashCode());
    }

    @Test
    public void stage2_createAndGetByIdTest() throws Exception {
        int maxId = CategoryDAOold.getInstance().getMaxId();
        expected = new Category(maxId + 1, "Test Category", null);
        CategoryDAOold.getInstance().create(expected);
        actual = CategoryDAOold.getInstance().getById(expected.getId());
        Assert.assertEquals("Create and read test not valid", expected, actual);
    }

    @Test
    public void stage3_getAllTest() throws Exception {
        List<Category> categoryList = CategoryDAOold.getInstance().getAll();
        Assert.assertTrue("GetAll test not valid", categoryList.size() > 0);
    }

    @Test
    public void stage4_updateTest() throws Exception {
        expected.setName("Test2");
        CategoryDAOold.getInstance().update(expected);
        actual = CategoryDAOold.getInstance().getById(expected.getId());
        Assert.assertEquals("Update test not valid", expected, actual);
    }

    @Test
    public void stage5_removeTest() throws Exception {
        CategoryDAOold.getInstance().remove(expected.getId());
        actual = CategoryDAOold.getInstance().getById(expected.getId());
        Assert.assertNull("Remove test not valid", actual);
    }
}
