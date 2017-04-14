package com.criticalgnome.blog.dao.impl;

import com.criticalgnome.blog.dao.ICategoryDao;
import com.criticalgnome.blog.entities.Category;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
public class CategoryDaoImpl extends DaoImpl<Category> implements ICategoryDao {

    private static volatile CategoryDaoImpl instance;

    private CategoryDaoImpl() {
        super(Category.class);
    }

    /**
     * Singleton pattern
     * @return dao instance
     */
    public static CategoryDaoImpl getInstance() {
        if (instance == null) {
            synchronized (CategoryDaoImpl.class) {
                if (instance == null) {
                    instance = new CategoryDaoImpl();
                }
            }
        }
        return instance;
    }

}