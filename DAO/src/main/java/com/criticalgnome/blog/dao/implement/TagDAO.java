package com.criticalgnome.blog.dao.implement;

import com.criticalgnome.blog.dao.AbstractDAO;
import com.criticalgnome.blog.dao.exceptions.DAOException;
import com.criticalgnome.blog.entities.Tag;

import java.util.List;

/**
 * Project Blog
 * Created on 20.03.2017.
 *
 * @author CriticalGnome
 */
public class TagDAO extends AbstractDAO<Tag> {

    @Override
    public void create(Tag entity) throws DAOException {

    }

    @Override
    public List<Tag> getAll() throws DAOException {
        return null;
    }

    @Override
    public Tag getById(int id) throws DAOException {
        return null;
    }

    @Override
    public void update(Tag entity) throws DAOException {

    }

    @Override
    public void remove(int id) throws DAOException {

    }

    @Override
    public int getMaxId() throws DAOException {
        return 0;
    }
}
