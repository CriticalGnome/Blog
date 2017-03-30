package com.criticalgnome.blog.dao.implement;

import com.criticalgnome.blog.entities.Tag;
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
public class TagDaoTestOld {

    private static Tag actual;
    private static Tag expected;

    @Test
    public void stage1_getInstanceTest() {
        TagDAOold instance1 = TagDAOold.getInstance();
        TagDAOold instance2 = TagDAOold.getInstance();
        Assert.assertEquals("Not singleton", instance1.hashCode(), instance2.hashCode());
    }

    @Test
    public void stage2_createAndGetByIdTest() throws Exception {
        int maxId = TagDAOold.getInstance().getMaxId();
        expected = new Tag(maxId + 1, "Test Role");
        TagDAOold.getInstance().create(expected);
        actual = TagDAOold.getInstance().getById(expected.getId());
        Assert.assertEquals("Create and read test not valid", expected, actual);
    }

    @Test
    public void stage3_getAllTest() throws Exception {
        List<Tag> roleList = TagDAOold.getInstance().getAll();
        Assert.assertTrue("GetAll test not valid", roleList.size() > 0);
    }

    @Test
    public void stage4_updateTest() throws Exception {
        expected.setName("Test2");
        TagDAOold.getInstance().update(expected);
        actual = TagDAOold.getInstance().getById(expected.getId());
        Assert.assertEquals("Update test not valid", expected, actual);
    }

    @Test
    public void stage5_removeTest() throws Exception {
        TagDAOold.getInstance().remove(expected.getId());
        actual = TagDAOold.getInstance().getById(expected.getId());
        Assert.assertNull("Remove test not valid", actual);
    }
}