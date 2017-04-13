package com.criticalgnome.blog.services.impl;

import com.criticalgnome.blog.dao.ICategoryDao;
import com.criticalgnome.blog.dao.IDao;
import com.criticalgnome.blog.dao.impl.CategoryDaoImpl;
import com.criticalgnome.blog.entities.Category;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.AbstractService;
import com.criticalgnome.blog.services.ICategoryService;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
public class CategoryServiceImpl extends AbstractService<Category> implements ICategoryService {

    private static volatile CategoryServiceImpl instance;
    private ICategoryDao categoryDao;

    private CategoryServiceImpl(ICategoryDao categoryDao) {
        super(categoryDao);
        this.categoryDao = categoryDao;
    }

    public static CategoryServiceImpl getInstance() {
        if (instance == null) {
            synchronized (CategoryServiceImpl.class) {
                if (instance == null) {
                    instance = new CategoryServiceImpl();
                }
            }
        }
        return instance;
    }

}
