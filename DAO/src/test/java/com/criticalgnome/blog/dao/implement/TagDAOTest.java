package com.criticalgnome.blog.dao.implement;

import com.criticalgnome.blog.entities.Tag;
import com.criticalgnome.blog.utils.EntityConstructor;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;

/**
 * Project Blog
 * Created on 21.03.2017.
 *
 * @author CriticalGnome
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TagDAOTest {

    private static Tag actual;
    private static Tag expected;

    @Test
    public void stage1_getInstanceTest() {
        TagDAO instance1 = TagDAO.getInstance();
        TagDAO instance2 = TagDAO.getInstance();
        Assert.assertEquals("Not singleton", instance1.hashCode(), instance2.hashCode());
    }

    @Test
    public void stage2_createAndGetByIdTest() throws Exception {
        int maxId = TagDAO.getInstance().getMaxId();
        expected = EntityConstructor.buildTag(maxId + 1, "Test Role");
        TagDAO.getInstance().create(expected);
        actual = TagDAO.getInstance().getById(expected.getId());
        Assert.assertEquals("Create and read test not valid", expected, actual);
    }

    @Test
    public void stage3_getAllTest() throws Exception {
        List<Tag> roleList = TagDAO.getInstance().getAll();
        Assert.assertTrue("GetAll test not valid", roleList.size() > 0);
    }

    @Test
    public void stage4_updateTest() throws Exception {
        expected.setName("Test2");
        TagDAO.getInstance().update(expected);
        actual = TagDAO.getInstance().getById(expected.getId());
        Assert.assertEquals("Update test not valid", expected, actual);
    }

    @Test
    public void stage5_removeTest() throws Exception {
        TagDAO.getInstance().remove(expected.getId());
        actual = TagDAO.getInstance().getById(expected.getId());
        Assert.assertNull("Remove test not valid", actual);
    }
}
