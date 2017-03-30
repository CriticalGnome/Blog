package com.criticalgnome.blog.dao.implement;

import com.criticalgnome.blog.entities.Tag;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TagDaoTest {

    private static Tag expected;
    private static Tag actual;

    @Test
    public void test1() throws Exception {
        expected = new Tag(null, "Test tag");
        Long id = TagDao.getInstance().create(expected);
        actual = TagDao.getInstance().getById(id);
        Assert.assertEquals("Not equal", expected, actual);
    }

    @Test
    public void test2() throws Exception {
        expected.setName("New name for tag");
        TagDao.getInstance().update(expected);
        actual = TagDao.getInstance().getById(expected.getId());
        Assert.assertEquals("Not equal", expected, actual);
    }

    @Test
    public void test3() throws Exception {
        TagDao.getInstance().remove(expected.getId());
        actual = TagDao.getInstance().getById(expected.getId());
        Assert.assertNull("Not deleted", actual);
    }

}
