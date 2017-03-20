package com.criticalgnome.blog.dao.implement;

import com.criticalgnome.blog.entities.Category;
import com.criticalgnome.blog.utils.EntityConstructor;
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

    private int maxId;
    private Category expected;
    private Category actual;

    @Before
    public void setUp() throws Exception {
        maxId = CategoryDAO.getInstance().getMaxId();
        Category parentCategory = EntityConstructor.buildCategory(1, "Новости", null);
        expected = EntityConstructor.buildCategory(maxId + 1, "Test", parentCategory);
    }

    @After
    public void tearDown(){
        expected = null;
    }


    @Test
    public void stage1_getInstanceTest() {
        CategoryDAO instance1 = CategoryDAO.getInstance();
        CategoryDAO instance2 = CategoryDAO.getInstance();
        Assert.assertEquals("Not singleton", instance1.hashCode(), instance2.hashCode());
    }

    @Test
    public void stage2_createTest() throws Exception {
        CategoryDAO.getInstance().create(expected);
        actual = CategoryDAO.getInstance().getById(expected.getId());
        Assert.assertEquals("Create test not valid", expected, actual);
    }

    @Test
    public void stage3_getByIdTest() throws Exception {
        expected = EntityConstructor.buildCategory(0, "Без категории", null);
        actual = CategoryDAO.getInstance().getById(expected.getId());
        Assert.assertEquals("GetById test not valid", expected, actual);
    }

    @Test
    public void stage4_getAllTest() throws Exception {
        List<Category> categoryList = CategoryDAO.getInstance().getAll();
        Assert.assertTrue("GetAll test not valid", categoryList.size() > 0);
    }

    @Test
    public void stage5_updateTest() throws Exception {
        expected.setId(expected.getId() - 1);
        expected.setName("Test2");
        CategoryDAO.getInstance().update(expected);
        actual = CategoryDAO.getInstance().getById(expected.getId());
        Assert.assertEquals("Update test not valid", expected, actual);
    }

    @Test
    public void stage6_removeTest() throws Exception {
        CategoryDAO.getInstance().remove(expected.getId() - 1);
        actual = CategoryDAO.getInstance().getById(expected.getId() - 1);
        Assert.assertNull("Remove test not valid", actual);
    }

}
