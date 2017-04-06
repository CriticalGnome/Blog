package com.criticalgnome.blog.dao.implement;

import com.criticalgnome.blog.entities.*;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.HashSet;
import java.util.Set;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RecordDaoTest {

    private static Record expected;
    private static Record actual;

    @Test
    public void test1() throws Exception {
        Set<Tag> tags = new HashSet<>();
        expected = new Record(
                null,
                "Header",
                "Body text",
                null,
                null,
                null,
                null,
                tags);
        RecordDao.getInstance().create(expected);
        actual = RecordDao.getInstance().getById(expected.getId());
        Assert.assertEquals("Not equal", expected, actual);
    }

    @Test
    public void test2() throws Exception {
        expected.setHeader("New Header");
        expected.setBody("New body text");
        RecordDao.getInstance().update(expected);
        actual = RecordDao.getInstance().getById(expected.getId());
        Assert.assertEquals("Not equal", expected, actual);
    }

    @Test
    public void test3() throws Exception {
        RecordDao.getInstance().remove(expected.getId());
        actual = RecordDao.getInstance().getById(expected.getId());
        Assert.assertNull("Not deleted", actual);
    }

    @Test
    public void test4() throws Exception {
        int count1 = RecordDao.getInstance().getRecordsCount();
        Long i = RecordDao.getInstance().create(expected);
        int count2 = RecordDao.getInstance().getRecordsCount();
        RecordDao.getInstance().remove(expected.getId());
        int count3 = RecordDao.getInstance().getRecordsCount();
        Assert.assertEquals("Not valid", count1 + 1, count2);
        Assert.assertEquals("Not valid", count2 - 1, count3);
    }
}
