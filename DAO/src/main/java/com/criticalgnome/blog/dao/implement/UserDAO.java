package com.criticalgnome.blog.dao.implement;

import com.criticalgnome.blog.constants.SqlSynatx;
import com.criticalgnome.blog.constants.SqlTables;
import com.criticalgnome.blog.dao.AbstractDAO;
import com.criticalgnome.blog.exceptions.DAOException;
import com.criticalgnome.blog.utils.ConnectionPool;
import com.criticalgnome.blog.entities.Role;
import com.criticalgnome.blog.entities.User;
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
public class UserDAO extends AbstractDAO<User> {

    private static volatile UserDAO instance;
    private static final Logger logger = LogManager.getLogger(UserDAO.class);

    private UserDAO() {}

    public static UserDAO getInstance() {
        if (instance == null) {
            synchronized (UserDAO.class) {
                if (instance == null) {
                    instance = new UserDAO();
                }
            }
        }
        return instance;
    }

    @Override
    public void create(User user) throws DAOException {
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SqlSynatx.INSERT_INTO_USER_VALUES);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getRole().getId());
            preparedStatement.setString(5, user.getNickName());
            preparedStatement.setString(6, user.getFirstName());
            preparedStatement.setString(7, user.getLastName());
            preparedStatement.executeUpdate();
            logger.log(Level.INFO, "Created new user {} \"{}\" {} [{}]", user.getFirstName(), user.getNickName(), user.getLastName(), user.getId());
        } catch (SQLException e) {
            logger.log(Level.FATAL, "MySQL fatal error in create method");
            throw new DAOException("MySQL Error", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public List<User> getAll() throws DAOException {
        List<User> userList = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SqlSynatx.SELECT_FROM_USER);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userList.add(buildUser(resultSet));
            }
        } catch (SQLException e) {
            logger.log(Level.FATAL, "MySQL fatal error in getAll method");
            throw new DAOException("MySQL Error", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return userList;
    }

    @Override
    public User getById(int id) throws DAOException {
        User user = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SqlSynatx.SELECT_FROM_USER_WHERE);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = buildUser(resultSet);
            }
        } catch (SQLException e) {
            logger.log(Level.FATAL, "MySQL fatal error in getById method");
            throw new DAOException("MySQL Error", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return user;
    }

    public User getByEmailAndPassword(String email, String password) throws DAOException {
        User user = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SqlSynatx.SELECT_FROM_USER_WHERE_EMAIL_AND_PASSWORD);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = buildUser(resultSet);
            }
        } catch (SQLException e) {
            logger.log(Level.FATAL, "MySQL fatal error in getByEmailAndPassword method");
            throw new DAOException("MySQL Error", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return user;
    }

    @Override
    public void update(User user) throws DAOException {
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SqlSynatx.UPDATE_USER_SET_WHERE);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getNickName());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getLastName());
            preparedStatement.setInt(6, user.getRole().getId());
            preparedStatement.setInt(7, user.getId());
            preparedStatement.executeUpdate();
            logger.log(Level.INFO, "Update user: {} \"{}\" {} [{}]", user.getFirstName(), user.getNickName(), user.getLastName(), user.getId());
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
            preparedStatement = connection.prepareStatement(SqlSynatx.DELETE_FROM_USER_WHERE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            logger.log(Level.INFO, "User [{}] removed", id);
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
            preparedStatement = connection.prepareStatement(SqlSynatx.SELECT_MAX_FROM_USER);
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

    private User buildUser(ResultSet resultSet) throws SQLException, DAOException {
        int id = resultSet.getInt(SqlTables.USER_ID);
        String email = resultSet.getString(SqlTables.USER_EMAIL);
        String password = resultSet.getString(SqlTables.USER_PASSWORD);
        String nickName = resultSet.getString(SqlTables.USER_NICK_NAME);
        String firstName = resultSet.getString(SqlTables.USER_FIRST_NAME);
        String lastName = resultSet.getString(SqlTables.USER_LAST_NAME);
        Role role = RoleDAO.getInstance().getById(resultSet.getInt(SqlTables.USER_ROLE_ID));
        return new User(id, email, password,nickName, firstName, lastName, role);
    }
}
