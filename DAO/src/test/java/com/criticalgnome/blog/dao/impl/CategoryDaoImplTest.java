package com.criticalgnome.blog.dao.impl;

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
public class CategoryDaoImplTest {

    private CategoryDaoImpl categoryDaoImpl = CategoryDaoImpl.getInstance();

    @Test
    public void categoryDaoTesting() throws Exception {

        // Begin
        Session session = HibernateUtil.getInstance().getSession();
        Transaction transaction;
        Category expected = new Category(null, "New category", null);
        Category actual;

        // Create new category and subcategory
        transaction = session.beginTransaction();
        categoryDaoImpl.create(expected);
        actual = categoryDaoImpl.getById(expected.getId());
        transaction.commit();
        Assert.assertEquals("Not equal", expected, actual);

        // Update category with new parameters
        expected.setName("New name for category");
        transaction = session.beginTransaction();
        categoryDaoImpl.update(expected);
        actual = categoryDaoImpl.getById(expected.getId());
        transaction.commit();
        Assert.assertEquals("Not equal", expected, actual);

        // Remove category and subcategory
        transaction = session.beginTransaction();
        categoryDaoImpl.remove(expected.getId());
        actual = categoryDaoImpl.getById(expected.getId());
        transaction.commit();
        Assert.assertNull("Not deleted", actual);

        // End
        HibernateUtil.getInstance().releaseSession(session);
    }
}