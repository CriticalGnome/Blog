package com.criticalgnome.blog.services.impl;

import com.criticalgnome.blog.entities.Tag;
import com.criticalgnome.blog.services.ITagService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Project Blog
 * Created on 13.04.2017.
 *
 * @author CriticalGnome
 */
@ContextConfiguration("/context-services-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TagSeviceImplTest {

    @Autowired
    private ITagService tagService;

    @Test
    public void tagServiceCreateTest() throws Exception {
        Tag expected = new Tag(null, "New tag from Service");
        tagService.create(expected);
        Tag actual = tagService.getById(expected.getId());
        Assert.assertEquals("Not equal:", expected, actual);
        tagService.remove(expected.getId());
    }

    @Test
    public void tagServiceUpdateTest() throws Exception {
        Tag expected = new Tag(null, "New tag from Service");
        tagService.create(expected);
        expected.setName("New name for new tag");
        tagService.update(expected);
        Tag actual = tagService.getById(expected.getId());
        Assert.assertEquals("Not equal:", expected, actual);
        tagService.remove(expected.getId());
    }

    @Test
    public void tagServiceGetAllTest() throws Exception {
        int expected = tagService.getAll().size();
        Tag tag = new Tag(null, "New tag from Service");
        tagService.create(tag);
        int actual = tagService.getAll().size();
        Assert.assertEquals("Not equal:", expected, actual - 1);
        tagService.remove(tag.getId());
    }

//    @Test
//    public void recordDaoGetByNameTest() throws Exception {
//        Tag expected = new Tag(null, "New tag with super name");
//        tagService.create(expected);
//        Tag actual = tagService.getByName("New tag with super name");
//        Assert.assertEquals("Not equal:", expected, actual);
//        tagService.remove(expected.getId());
//    }

}
