package com.criticalgnome.blog.dao.implement;

import com.criticalgnome.blog.constants.SqlSynatx;
import com.criticalgnome.blog.constants.SqlTables;
import com.criticalgnome.blog.dao.AbstractDAO;
import com.criticalgnome.blog.exceptions.DAOException;
import com.criticalgnome.blog.utils.ConnectionPool;
import com.criticalgnome.blog.entities.Role;
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
public class RoleDAO extends AbstractDAO<Role> {

    private static volatile RoleDAO instance;
    private static final Logger logger = LogManager.getLogger(RoleDAO.class);

    private RoleDAO() {}

    public static RoleDAO getInstance() {
        if (instance == null) {
            synchronized (RoleDAO.class) {
                if (instance == null) {
                    instance = new RoleDAO();
                }
            }
        }
        return instance;
    }

    @Override
    public void create(Role role) throws DAOException {
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SqlSynatx.INSERT_INTO_ROLE_VALUES);
            preparedStatement.setInt(1, role.getId());
            preparedStatement.setString(2, role.getName());
            preparedStatement.executeUpdate();
            logger.log(Level.INFO, "Created new role {} [{}]", role.getName(), role.getId());
        } catch (SQLException e) {
            logger.log(Level.FATAL, "MySQL fatal error in create method");
            throw new DAOException("MySQL Error", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }

    }

    @Override
    public List<Role> getAll() throws DAOException {
        List<Role> roleList = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SqlSynatx.SELECT_FROM_ROLE);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                roleList.add(buildRole(resultSet));
            }
        } catch (SQLException e) {
            logger.log(Level.FATAL, "MySQL fatal error in getAll method");
            throw new DAOException("MySQL Error", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return roleList;
    }

    @Override
    public Role getById(int id) throws DAOException {
        Role role = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SqlSynatx.SELECT_FROM_ROLE_WHERE);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                role = buildRole(resultSet);
            }
        } catch (SQLException e) {
            logger.log(Level.FATAL, "MySQL fatal error in getById method");
            throw new DAOException("MySQL Error", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return role;
    }

    @Override
    public void update(Role role) throws DAOException {
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SqlSynatx.UPDATE_ROLE_SET_WHERE);
            preparedStatement.setString(1, role.getName());
            preparedStatement.setInt(2, role.getId());
            preparedStatement.executeUpdate();
            logger.log(Level.INFO, "Update role: {} [{}]", role.getName(), role.getId());
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
            preparedStatement = connection.prepareStatement(SqlSynatx.DELETE_FROM_ROLE_WHERE);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            logger.log(Level.INFO, "Role [{}] removed", id);
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
            preparedStatement = connection.prepareStatement(SqlSynatx.SELECT_MAX_FROM_ROLE);
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

    private Role buildRole(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(SqlTables.ROLE_ID);
        String name = resultSet.getString(SqlTables.ROLE_NAME);
        return EntityConstructor.buildRole(id, name);
    }
}
