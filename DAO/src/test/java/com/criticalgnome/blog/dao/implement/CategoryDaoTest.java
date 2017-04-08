package com.criticalgnome.blog.dao.implement;

import com.criticalgnome.blog.entities.Category;
import com.criticalgnome.blog.utils.HibernateConnector;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
public class CategoryDaoTest {

    private HibernateConnector connector = HibernateConnector.getInstance();
    private CategoryDao categoryDao = CategoryDao.getInstance();

    @Test
    public void categoryDaoTesting() throws Exception {

        // Open session
        Session session = connector.getSession();

        // Create new category and subcategory
        Category expected = new Category(null, "New category", null);
        Transaction transaction = session.beginTransaction();
        categoryDao.create(expected);
        Category actual = categoryDao.getById(expected.getId());
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
        actual = CategoryDao.getInstance().getById(expected.getId());
        transaction.commit();
        Assert.assertNull("Not deleted", actual);

        // Release session
        connector.releaseSession(session);
    }
}
