package com.criticalgnome.blog.dao.implement;

import com.criticalgnome.blog.dao.AbstractDAO;
import com.criticalgnome.blog.dao.exceptions.DAOException;
import com.criticalgnome.blog.entities.Record;

import java.util.List;

/**
 * Project Blog
 * Created on 20.03.2017.
 *
 * @author CriticalGnome
 */
public class RecordDAO extends AbstractDAO<Record> {

    @Override
    public void create(Record entity) throws DAOException {

    }

    @Override
    public List<Record> getAll() throws DAOException {
        return null;
    }

    @Override
    public Record getById(int id) throws DAOException {
        return null;
    }

    @Override
    public void update(Record entity) throws DAOException {

    }

    @Override
    public void remove(int id) throws DAOException {

    }

    @Override
    public int getMaxId() throws DAOException {
        return 0;
    }
}
