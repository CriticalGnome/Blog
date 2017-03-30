package com.criticalgnome.blog.dao;

import com.criticalgnome.blog.entities.Pojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Project Blog
 * Created on 20.03.2017.
 *
 * @author CriticalGnome
 */
public abstract class AbstractDAOold<T extends Pojo> implements Dao<T> {
    protected Connection connection;
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;
}
