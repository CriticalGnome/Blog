package com.criticalgnome.blog.dao;

import com.criticalgnome.blog.entities.Pojo;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Project Blog
 * Created on 20.03.2017.
 *
 * @author CriticalGnome
 */
public abstract class AbstractDAO<T extends Pojo> implements DAO<T> {
    protected Connection connection;
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;

    protected Session session;
}
