package com.criticalgnome.blog.services.impl;

import com.criticalgnome.blog.entities.Record;
import com.criticalgnome.blog.entities.Tag;
import com.criticalgnome.blog.services.IRecordService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

/**
 * Project Blog
 * Created on 13.04.2017.
 *
 * @author CriticalGnome
 */
@ContextConfiguration("/context-services-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class RecordServiceImplTest {

    @Autowired
    private IRecordService recordService;

    @Test
    public void recordServiceCreateTest() throws Exception {
        Record expected = new Record(null, "Test Header", "", null, null, null, null, null);
        recordService.create(expected);
        Record actual = recordService.getById(expected.getId());
        Assert.assertEquals("Not equal:", expected, actual);
        recordService.remove(expected.getId());
    }

    @Test
    public void recordDaoUpdateTest() throws Exception {
        Record expected = new Record(null, "Test Header", "", null, null, null, null, null);
        recordService.create(expected);
        expected.setHeader("New header for new record");
        recordService.update(expected);
        Record actual = recordService.getById(expected.getId());
        Assert.assertEquals("Not equal:", expected, actual);
        recordService.remove(expected.getId());
    }

    @Test
    public void recordDaoGetAllTest() throws Exception {
        int expected = recordService.getAll().size();
        Record record = new Record(null, "Test Header", "", null, null, null, null, null);
        recordService.create(record);
        int actual = recordService.getAll().size();
        Assert.assertEquals("Not equal:", expected, actual - 1);
        recordService.remove(record.getId());
    }

    @Test
    public void recordPaginationTest() throws Exception {
        int expected = 3;
        int actual = recordService.getRecordsByPage(1, 3).size();
        Assert.assertTrue("Not equals", actual <= expected);
    }

    @Test
    public void recordDaoCountTest() throws Exception {
        int expected = recordService.getRecordsCount();
        Set<Tag> tags = new HashSet<>();
        Record record = new Record(null,"Header from Service","Body text",null,null,null,null,tags);
        recordService.create(record);
        int actual = recordService.getRecordsCount();
        Assert.assertEquals("Not equal:", expected, actual - 1);
        recordService.remove(record.getId());
    }

}
