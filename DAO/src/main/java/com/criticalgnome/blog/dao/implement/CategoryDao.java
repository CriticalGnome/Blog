package com.criticalgnome.blog.dao.implement;

import com.criticalgnome.blog.dao.AbstractDao;
import com.criticalgnome.blog.entities.Category;
import com.criticalgnome.blog.exceptions.DaoException;
import org.hibernate.HibernateException;

import java.io.Serializable;
import java.util.List;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
public class CategoryDao extends AbstractDao<Category> {

    private static volatile CategoryDao instance;

    private CategoryDao() {
        super(Category.class);
    }

    /**
     * Singleton pattern
     * @return dao instance
     */
    public static CategoryDao getInstance() {
        if (instance == null) {
            synchronized (CategoryDao.class) {
                if (instance == null) {
                    instance = new CategoryDao();
                }
            }
        }
        return instance;
    }

}