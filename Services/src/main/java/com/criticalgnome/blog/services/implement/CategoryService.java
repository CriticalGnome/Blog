package com.criticalgnome.blog.services.implement;

import com.criticalgnome.blog.dao.implement.CategoryDao;
import com.criticalgnome.blog.entities.Category;
import com.criticalgnome.blog.exceptions.DAOException;
import com.criticalgnome.blog.exceptions.ServiceException;
import com.criticalgnome.blog.services.AbstractService;
import lombok.extern.log4j.Log4j2;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
@Log4j2
public class CategoryService extends AbstractService<Category> {

    private static volatile CategoryService instance;

    private CategoryService() {}

    public static CategoryService getInstance() {
        if (instance == null) {
            synchronized (CategoryService.class) {
                if (instance == null) {
                    instance = new CategoryService();
                }
            }
        }
        return instance;
    }

    @Override
    public Long create(Category category) throws DAOException, ServiceException {
        return CategoryDao.getInstance().create(category);
    }

    @Override
    public Category getById(Long id) throws DAOException, ServiceException {
        return CategoryDao.getInstance().getById(id);
    }

    @Override
    public void update(Category category) throws DAOException, ServiceException {
        CategoryDao.getInstance().update(category);
    }

    @Override
    public void remove(Long id) throws DAOException, ServiceException {
        CategoryDao.getInstance().remove(id);
    }
}
