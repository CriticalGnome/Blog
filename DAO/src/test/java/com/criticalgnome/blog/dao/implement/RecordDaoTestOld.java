package com.criticalgnome.blog.dao.implement;

import com.criticalgnome.blog.entities.Category;
import com.criticalgnome.blog.entities.Record;
import com.criticalgnome.blog.entities.Tag;
import com.criticalgnome.blog.entities.User;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Project Blog
 * Created on 21.03.2017.
 *
 * @author CriticalGnome
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RecordDaoTestOld {

    private static Record actual;
    private static Record expected;

    @Test
    public void stage1_getInstanceTest() {
        RecordDAOold instance1 = RecordDAOold.getInstance();
        RecordDAOold instance2 = RecordDAOold.getInstance();
        Assert.assertEquals("Not singleton", instance1.hashCode(), instance2.hashCode());
    }

    @Test
    public void stage2_createAndGetByIdTest() throws Exception {
        int maxId = RecordDAOold.getInstance().getMaxId();
        Category category = CategoryDAOold.getInstance().getById(1);
        User user = UserDAOold.getInstance().getById(1);
        List<Tag> tagList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        Timestamp timestamp = new Timestamp(now.getTime());
        expected = new Record(maxId + 1, "Test Header", "Test Body", timestamp, category, user, tagList);
        RecordDAOold.getInstance().create(expected);
        actual = RecordDAOold.getInstance().getById(expected.getId());
        expected.setTimestamp(actual.getTimestamp());
        Assert.assertEquals("Create and read test not valid", expected, actual);
    }

    @Test
    public void stage3_getAllTest() throws Exception {
        List<Record> recordList = RecordDAOold.getInstance().getAll();
        Assert.assertTrue("GetAll test not valid", recordList.size() > 0);
    }

    @Test
    public void stage4_updateTest() throws Exception {
        expected.setHeader("New Header");
        RecordDAOold.getInstance().update(expected);
        actual = RecordDAOold.getInstance().getById(expected.getId());
        Assert.assertEquals("Update test not valid", expected, actual);
    }

    @Test
    public void stage5_removeTest() throws Exception {
        RecordDAOold.getInstance().remove(expected.getId());
        actual = RecordDAOold.getInstance().getById(expected.getId());
        Assert.assertNull("Remove test not valid", actual);
    }
}