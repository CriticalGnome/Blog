package com.criticalgnome.blog.services.impl;

import com.criticalgnome.blog.entities.Tag;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Project Blog
 * Created on 13.04.2017.
 *
 * @author CriticalGnome
 */
public class TagSeviceImplTest {

    private TagServiceImpl tagService = TagServiceImpl.getInstance();

    @Test
    public void testingCategoryService() throws Exception {
        Tag expected = new Tag(null, "Test name");
        tagService.create(expected);
        Tag actual = tagService.getById(expected.getId());
        Assert.assertEquals("Not equals", expected, actual);
        expected.setName("New name for test tag");
        tagService.update(expected);
        actual = tagService.getById(expected.getId());
        Assert.assertEquals("Not equals", expected, actual);
        List<Tag> categories = tagService.getAll();
        Assert.assertTrue("No list", categories.size() > 0);
        tagService.remove(expected.getId());
        actual = tagService.getById(expected.getId());
        Assert.assertNull("Not deleted", actual);
    }

}
