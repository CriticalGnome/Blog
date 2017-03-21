package com.criticalgnome.blog.dao.implement;

import com.criticalgnome.blog.entities.Category;
import com.criticalgnome.blog.entities.Record;
import com.criticalgnome.blog.entities.Tag;
import com.criticalgnome.blog.entities.User;
import com.criticalgnome.blog.utils.EntityConstructor;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Blog
 * Created on 21.03.2017.
 *
 * @author CriticalGnome
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RecordDAOTest {

    private static Record actual;
    private static Record expected;

    @Test
    public void stage1_getInstanceTest() {
        RecordDAO instance1 = RecordDAO.getInstance();
        RecordDAO instance2 = RecordDAO.getInstance();
        Assert.assertEquals("Not singleton", instance1.hashCode(), instance2.hashCode());
    }

    @Test
    public void stage2_createAndGetByIdTest() throws Exception {
        int maxId = RecordDAO.getInstance().getMaxId();
        Category category = CategoryDAO.getInstance().getById(1);
        User user = UserDAO.getInstance().getById(1);
        List<Tag> tagList = new ArrayList<>();
        expected = EntityConstructor.buildRecord(maxId + 1, "Test Header", "Test Body", null, category, user, tagList);
        RecordDAO.getInstance().create(expected);
        actual = RecordDAO.getInstance().getById(expected.getId());
        expected.setTimestamp(actual.getTimestamp());
        Assert.assertEquals("Create and read test not valid", expected, actual);
    }

    @Test
    public void stage3_getAllTest() throws Exception {
        List<Record> recordList = RecordDAO.getInstance().getAll();
        Assert.assertTrue("GetAll test not valid", recordList.size() > 0);
    }

    @Test
    public void stage4_updateTest() throws Exception {
        expected.setHeader("New Header");
        RecordDAO.getInstance().update(expected);
        actual = RecordDAO.getInstance().getById(expected.getId());
        Assert.assertEquals("Update test not valid", expected, actual);
    }

    @Test
    public void stage5_removeTest() throws Exception {
        RecordDAO.getInstance().remove(expected.getId());
        actual = RecordDAO.getInstance().getById(expected.getId());
        Assert.assertNull("Remove test not valid", actual);
    }
}