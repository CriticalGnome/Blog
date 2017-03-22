package com.criticalgnome.blog.dao.implement;

import com.criticalgnome.blog.constants.SqlSynatx;
import com.criticalgnome.blog.constants.SqlTables;
import com.criticalgnome.blog.dao.AbstractDAO;
import com.criticalgnome.blog.exceptions.DAOException;
import com.criticalgnome.blog.utils.ConnectionPool;
import com.criticalgnome.blog.entities.Category;
import com.criticalgnome.blog.entities.Record;
import com.criticalgnome.blog.entities.Tag;
import com.criticalgnome.blog.entities.User;
import com.criticalgnome.blog.utils.EntityConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Project Blog
 * Created on 20.03.2017.
 *
 * @author CriticalGnome
 */
public class RecordDAO extends AbstractDAO<Record> {

    private static volatile RecordDAO instance;
    private static final Logger logger = LogManager.getLogger(RecordDAO.class);

    private RecordDAO() {}

    public static RecordDAO getInstance() {
        if (instance == null) {
            synchronized (RecordDAO.class) {
                if (instance == null) {
                    instance = new RecordDAO();
                }
            }
        }
        return instance;
    }

    @Override
    public void create(Record record) throws DAOException {
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SqlSynatx.INSERT_INTO_RECORD_VALUES);
            preparedStatement.setInt(1, record.getId());
            preparedStatement.setString(2, record.getHeader());
            preparedStatement.setString(3, record.getBody());
            preparedStatement.setInt(4, record.getCategory().getId());
            preparedStatement.setInt(5, record.getAuthor().getId());
            preparedStatement.executeUpdate();
            logger.log(Level.INFO, "Created new record \"{}\" [{}]", record.getHeader(), record.getId());
        } catch (SQLException e) {
            logger.log(Level.FATAL, "MySQL fatal error in create method");
            throw new DAOException("MySQL Error", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public List<Record> getAll() throws DAOException {
        List<Record> recordList = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SqlSynatx.SELECT_FROM_RECORD);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                recordList.add(buildRecord(resultSet));
            }
        } catch (SQLException e) {
            logger.log(Level.FATAL, "MySQL fatal error in create method");
            throw new DAOException("MySQL Error", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return recordList;
    }

    @Override
    public Record getById(int id) throws DAOException {
        Record record = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SqlSynatx.SELECT_FROM_RECORD_WHERE);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                record = buildRecord(resultSet);
            }
        } catch (SQLException e) {
            logger.log(Level.FATAL, "MySQL fatal error in getById method");
            throw new DAOException("MySQL Error", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return record;
    }

    @Override
    public void update(Record record) throws DAOException {
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SqlSynatx.UPDATE_RECORD_SET_WHERE);
            preparedStatement.setString(1, record.getHeader());
            preparedStatement.setString(2, record.getBody());
            preparedStatement.setInt(3, record.getCategory().getId());
            preparedStatement.setInt(4, record.getAuthor().getId());
            preparedStatement.setInt(5, record.getId());
            preparedStatement.executeUpdate();
            logger.log(Level.INFO, "Update record: {} [{}]", record.getHeader(), record.getId());
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
            preparedStatement = connection.prepareStatement(SqlSynatx.DELETE_FROM_RECORD_WHERE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            logger.log(Level.INFO, "Record [{}] removed", id);
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
            preparedStatement = connection.prepareStatement(SqlSynatx.SELECT_MAX_FROM_RECORD);
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

    private Record buildRecord(ResultSet resultSet) throws SQLException, DAOException {
        int id = resultSet.getInt(SqlTables.RECORD_ID);
        String header = resultSet.getString(SqlTables.RECORD_HEADER);
        String body = resultSet.getString(SqlTables.RECORD_BODY);
        Timestamp timestamp = resultSet.getTimestamp(SqlTables.RECORD_DATE);
        Category category = CategoryDAO.getInstance().getById(resultSet.getInt(SqlTables.RECORD_CATEGORY_ID));
        User user = UserDAO.getInstance().getById(resultSet.getInt(SqlTables.RECORD_USER_ID));
        List<Tag> tagList = TagDAO.getInstance().getTagsForRecord(resultSet.getInt("id"));
        return EntityConstructor.buildRecord(id, header, body, timestamp, category, user, tagList);
    }
}
