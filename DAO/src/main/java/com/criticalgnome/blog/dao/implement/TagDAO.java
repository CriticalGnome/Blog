package com.criticalgnome.blog.dao.implement;

import com.criticalgnome.blog.constants.SqlSynatx;
import com.criticalgnome.blog.constants.SqlTables;
import com.criticalgnome.blog.dao.AbstractDAO;
import com.criticalgnome.blog.exceptions.DAOException;
import com.criticalgnome.blog.utils.ConnectionPool;
import com.criticalgnome.blog.entities.Tag;
import com.criticalgnome.blog.utils.EntityConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Project Blog
 * Created on 20.03.2017.
 *
 * @author CriticalGnome
 */
public class TagDAO extends AbstractDAO<Tag> {

    private static volatile TagDAO instance;
    private static final Logger logger = LogManager.getLogger(TagDAO.class);

    private TagDAO() {}

    public static TagDAO getInstance() {
        if (instance == null) {
            synchronized (TagDAO.class) {
                if (instance == null) {
                    instance = new TagDAO();
                }
            }
        }
        return instance;
    }

    @Override
    public void create(Tag tag) throws DAOException {
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SqlSynatx.INSERT_INTO_TAG_VALUES);
            preparedStatement.setInt(1, tag.getId());
            preparedStatement.setString(2, tag.getName());
            preparedStatement.executeUpdate();
            logger.log(Level.INFO, "Created new tag {} [{}]", tag.getName(), tag.getId());
        } catch (SQLException e) {
            logger.log(Level.FATAL, "MySQL fatal error in create method");
            throw new DAOException("MySQL Error", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }

    }

    @Override
    public List<Tag> getAll() throws DAOException {
        List<Tag> tagList = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SqlSynatx.SELECT_FROM_TAG);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tagList.add(buildTag(resultSet));
            }
        } catch (SQLException e) {
            logger.log(Level.FATAL, "MySQL fatal error in getAll method");
            throw new DAOException("MySQL Error", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return tagList;
    }

    @Override
    public Tag getById(int id) throws DAOException {
        Tag tag = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SqlSynatx.SELECT_FROM_TAG_WHERE);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tag = buildTag(resultSet);
            }
        } catch (SQLException e) {
            logger.log(Level.FATAL, "MySQL fatal error in getById method");
            throw new DAOException("MySQL Error", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return tag;
    }

    @Override
    public void update(Tag tag) throws DAOException {
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SqlSynatx.UPDATE_TAG_SET_WHERE);
            preparedStatement.setString(1, tag.getName());
            preparedStatement.setInt(2, tag.getId());
            preparedStatement.executeUpdate();
            logger.log(Level.INFO, "Update tag: {} [{}]", tag.getName(), tag.getId());
        } catch (SQLException e) {
            logger.log(Level.FATAL, "MySQL fatal error in update method");
            throw new DAOException("MySQL Error", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public void remove(int id) throws DAOException {
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SqlSynatx.DELETE_FROM_TAG_WHERE);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            logger.log(Level.INFO, "Tag [{}] removed", id);
        } catch (SQLException e) {
            logger.log(Level.FATAL, "MySQL fatal error in remove method");
            throw new DAOException("MySQL Error", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public int getMaxId() throws DAOException {
        int maxId = -1;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SqlSynatx.SELECT_MAX_FROM_TAG);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                maxId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.log(Level.FATAL, "MySQL fatal error in getMaxId method");
            throw new DAOException("MySQL Error", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return maxId;
    }

    public List<Tag> getTagsForRecord(int id) throws DAOException {
        List<Tag> tagList = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SqlSynatx.SELECT_FROM_TAG_WHERE_RECORD_HAS_TAG_RECORD_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tagList.add(buildTag(resultSet));
            }
        } catch (SQLException e) {
            logger.log(Level.FATAL, "MySQL fatal error in getTagsForRecord method");
            throw new DAOException("MySQL Error", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return tagList;
    }

    private Tag buildTag(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(SqlTables.TAG_ID);
        String name = resultSet.getString(SqlTables.TAG_NAME);
        return EntityConstructor.buildTag(id, name);
    }
}
