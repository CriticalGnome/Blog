package com.criticalgnome.blog.dao.impl;

import com.criticalgnome.blog.dao.ICategoryDao;
import com.criticalgnome.blog.entities.Category;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Project Blog
 * Created on 30.03.2017.
 *
 * @author CriticalGnome
 */
@Repository
public class CategoryDaoImpl extends DaoImpl<Category> implements ICategoryDao {

    @Autowired
    private CategoryDaoImpl(SessionFactory sessionFactory) {
        super(Category.class, sessionFactory);
    }

}