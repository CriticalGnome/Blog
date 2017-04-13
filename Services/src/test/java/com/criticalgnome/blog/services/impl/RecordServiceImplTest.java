package com.criticalgnome.blog.services.impl;

import com.criticalgnome.blog.entities.Record;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Project Blog
 * Created on 13.04.2017.
 *
 * @author CriticalGnome
 */
public class RecordServiceImplTest {
    
    private RecordServiceImpl recordService = RecordServiceImpl.getInstance();
    
    @Test
    public void testingCategoryService() throws Exception {
        Record expected = new Record(null, "Test Header", "", null, null, null, null, null);
        recordService.create(expected);
        Record actual = recordService.getById(expected.getId());
        Assert.assertEquals("Not equals", expected, actual);
        expected.setHeader("New header for test record");
        recordService.update(expected);
        actual = recordService.getById(expected.getId());
        Assert.assertEquals("Not equals", expected, actual);
        List<Record> categories = recordService.getAll();
        Assert.assertTrue("No list", categories.size() > 0);
        recordService.remove(expected.getId());
        actual = recordService.getById(expected.getId());
        Assert.assertNull("Not deleted", actual);
    }
}
