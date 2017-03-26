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
public class CategoryDAOTest {

    private static Category actual;
    private static Category expected;

    @Test
    public void stage1_getInstanceTest() {
        CategoryDAO instance1 = CategoryDAO.getInstance();
        CategoryDAO instance2 = CategoryDAO.getInstance();
        Assert.assertEquals("Not singleton", instance1.hashCode(), instance2.hashCode());
    }

    @Test
    public void stage2_createAndGetByIdTest() throws Exception {
        int maxId = CategoryDAO.getInstance().getMaxId();
        expected = new Category(maxId + 1, "Test Category", null);
        CategoryDAO.getInstance().create(expected);
        actual = CategoryDAO.getInstance().getById(expected.getId());
        Assert.assertEquals("Create and read test not valid", expected, actual);
    }

    @Test
    public void stage3_getAllTest() throws Exception {
        List<Category> categoryList = CategoryDAO.getInstance().getAll();
        Assert.assertTrue("GetAll test not valid", categoryList.size() > 0);
    }

    @Test
    public void stage4_updateTest() throws Exception {
        expected.setName("Test2");
        CategoryDAO.getInstance().update(expected);
        actual = CategoryDAO.getInstance().getById(expected.getId());
        Assert.assertEquals("Update test not valid", expected, actual);
    }

    @Test
    public void stage5_removeTest() throws Exception {
        CategoryDAO.getInstance().remove(expected.getId());
        actual = CategoryDAO.getInstance().getById(expected.getId());
        Assert.assertNull("Remove test not valid", actual);
    }
}
