package com.criticalgnome.blog.services.impl;

import com.criticalgnome.blog.dao.ICategoryDao;
import com.criticalgnome.blog.entities.Category;
import com.criticalgnome.blog.exceptions.DaoException;
import com.criticalgnome.blog.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = DaoException.class)
public class CategoryServiceImpl extends ServiceImpl<Category> implements ICategoryService {

    @Autowired
    private CategoryServiceImpl(ICategoryDao categoryDao) {
        super(categoryDao);
    }

}
