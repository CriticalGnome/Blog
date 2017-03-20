package com.criticalgnome.blog.dao;

import com.criticalgnome.blog.dao.exceptions.DAOException;
import com.criticalgnome.blog.entities.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Project Blog
 * Created on 20.03.2017.
 *
 * @author CriticalGnome
 */
public abstract class AbstractDAO<T extends Entity> implements DAO<T> {
    protected Connection connection;
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;
}
