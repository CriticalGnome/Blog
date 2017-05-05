package com.criticalgnome.blog.dao.impl;

import com.criticalgnome.blog.dao.IRecordDao;
import com.criticalgnome.blog.entities.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
@ContextConfiguration("/context-dao-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional(transactionManager = "transactionManager")
public class RecordDaoImplTest {

    @Autowired
    private IRecordDao recordDao;

    @Test
    public void recordDaoCreateTest() throws Exception {
        Set<Tag> tags = new HashSet<>();
        Record expected = new Record(null,"Header","Body text",null,null,null,null,tags);
        recordDao.create(expected);
        Record actual = recordDao.getById(expected.getId());
        Assert.assertEquals("Not equal:", expected, actual);
        recordDao.remove(expected.getId());
    }

    @Test
    public void recordDaoUpdateTest() throws Exception {
        Set<Tag> tags = new HashSet<>();
        Record expected = new Record(null,"Header","Body text",null,null,null,null,tags);
        recordDao.create(expected);
        expected.setHeader("New header for new record");
        recordDao.update(expected);
        Record actual = recordDao.getById(expected.getId());
        Assert.assertEquals("Not equal:", expected, actual);
        recordDao.remove(expected.getId());
    }

    @Test
    public void recordDaoGetAllTest() throws Exception {
        int expected = recordDao.getAll().size();
        Set<Tag> tags = new HashSet<>();
        Record record = new Record(null,"Header","Body text",null,null,null,null,tags);
        recordDao.create(record);
        int actual = recordDao.getAll().size();
        Assert.assertEquals("Not equal:", expected, actual - 1);
        recordDao.remove(record.getId());
    }

    @Test
    public void recordPaginationTest() throws Exception {
        int expected = 3;
        int actual = recordDao.getRecordsByPage(0, 3, null, null, null).size();
        Assert.assertTrue("Not equals", actual <= expected);
    }

    @Test
    public void recordDaoCountTest() throws Exception {
        int expected = recordDao.getRecordsCount(null, null, null);
        Set<Tag> tags = new HashSet<>();
        Record record = new Record(null,"Header","Body text",null,null,null,null,tags);
        recordDao.create(record);
        int actual = recordDao.getRecordsCount(null, null, null);
        Assert.assertEquals("Not equal:", expected, actual - 1);
        recordDao.remove(record.getId());
    }

}
