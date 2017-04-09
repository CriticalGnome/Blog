package com.criticalgnome.blog.dao.implement;

import com.criticalgnome.blog.entities.Category;
import com.criticalgnome.blog.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
public class CategoryDaoTest {

    private CategoryDao categoryDao = CategoryDao.getInstance();

    @Test
    public void categoryDaoTesting() throws Exception {

        // Begin
        Session session = HibernateUtil.getInstance().getSession();
        Transaction transaction;
        Category expected = new Category(null, "New category", null);
        Category actual;

        // Create new category and subcategory
        transaction = session.beginTransaction();
        categoryDao.create(expected);
        actual = categoryDao.getById(expected.getId());
        transaction.commit();
        Assert.assertEquals("Not equal", expected, actual);

        // Update category with new parameters
        expected.setName("New name for category");
        transaction = session.beginTransaction();
        categoryDao.update(expected);
        actual = categoryDao.getById(expected.getId());
        transaction.commit();
        Assert.assertEquals("Not equal", expected, actual);

        // Remove category and subcategory
        transaction = session.beginTransaction();
        categoryDao.remove(expected.getId());
        actual = categoryDao.getById(expected.getId());
        transaction.commit();
        Assert.assertNull("Not deleted", actual);

        // End
        HibernateUtil.getInstance().releaseSession(session);
    }
}
