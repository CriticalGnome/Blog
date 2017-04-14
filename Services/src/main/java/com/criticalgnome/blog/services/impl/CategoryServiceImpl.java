package com.criticalgnome.blog.services.impl;

import com.criticalgnome.blog.dao.ICategoryDao;
import com.criticalgnome.blog.dao.IDao;
import com.criticalgnome.blog.dao.impl.CategoryDaoImpl;
import com.criticalgnome.blog.entities.Category;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.ICategoryService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
public class CategoryServiceImpl extends ServiceImpl<Category> implements ICategoryService {

    private static volatile CategoryServiceImpl instance;
    private CategoryDaoImpl categoryDao = CategoryDaoImpl.getInstance();
    private ICategoryDao iCategoryDao;

    private CategoryServiceImpl() {}

    private CategoryServiceImpl(IDao<Category> iDao) {
        super(iDao);
        this.iCategoryDao = (ICategoryDao) iDao;
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
