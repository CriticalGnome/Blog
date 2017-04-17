package com.criticalgnome.blog.dao.impl;

import com.criticalgnome.blog.dao.ITagDao;
import com.criticalgnome.blog.entities.Tag;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
@ContextConfiguration("/context-dao-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional(transactionManager = "transactionManager")
public class TagDaoImplTest {
    
    @Autowired
    private ITagDao tagDao;

    @Test
    public void tagDaoCreateTest() throws Exception {
        Tag expected = new Tag(null, "New tag");
        tagDao.create(expected);
        Tag actual = tagDao.getById(expected.getId());
        Assert.assertEquals("Not equal:", expected, actual);
        tagDao.remove(expected.getId());
    }

    @Test
    public void tagDaoUpdateTest() throws Exception {
        Tag expected = new Tag(null, "New tag");
        tagDao.create(expected);
        expected.setName("New name for new tag");
        tagDao.update(expected);
        Tag actual = tagDao.getById(expected.getId());
        Assert.assertEquals("Not equal:", expected, actual);
        tagDao.remove(expected.getId());
    }

    @Test
    public void tagDaoGetAllTest() throws Exception {
        int expected = tagDao.getAll().size();
        Tag tag = new Tag(null, "New tag");
        tagDao.create(tag);
        int actual = tagDao.getAll().size();
        Assert.assertEquals("Not equal:", expected, actual - 1);
        tagDao.remove(tag.getId());
    }

    @Test
    public void recordDaoGetByNameTest() throws Exception {
        Tag expected = new Tag(null, "New tag with super name");
        tagDao.create(expected);
        Tag actual = tagDao.getByName("New tag with super name");
        Assert.assertEquals("Not equal:", expected, actual);
        tagDao.remove(expected.getId());
    }

}
