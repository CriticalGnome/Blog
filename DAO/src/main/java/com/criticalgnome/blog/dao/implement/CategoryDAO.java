package com.criticalgnome.blog.dao.implement;

import com.criticalgnome.blog.constants.SqlSynatx;
import com.criticalgnome.blog.constants.SqlTables;
import com.criticalgnome.blog.dao.AbstractDAO;
import com.criticalgnome.blog.dao.exceptions.DAOException;
import com.criticalgnome.blog.dao.utils.ConnectionPool;
import com.criticalgnome.blog.entities.Category;
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
public class CategoryDAO extends AbstractDAO<Category> {

    private static volatile CategoryDAO instance;
    private static final Logger logger = LogManager.getLogger(CategoryDAO.class);

    private CategoryDAO(){}

    /**
     * Singleton
     */
    public static CategoryDAO getInstance() {
        if (instance == null) {
            synchronized (CategoryDAO.class) {
                if (instance == null) {
                    instance = new CategoryDAO();
                }
            }
        }
        return instance;
    }

    /**
     * Create new category
     * @param category category object
     * @throws DAOException custom exception
     */
    @Override
    public void create(Category category) throws DAOException {
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SqlSynatx.INSERT_INTO_CATEGORY_VALUES);
            preparedStatement.setInt(1, category.getId());
            preparedStatement.setString(2, category.getName());
            if (category.getCategory() != null) {
                preparedStatement.setInt(3, category.getCategory().getId());
            } else {
                preparedStatement.setInt(3, 0);
            }
            preparedStatement.executeUpdate();
            logger.log(Level.INFO, "Add new Category: {} [{}]", category.getName(), category.getId());
        } catch (SQLException e) {
            logger.log(Level.FATAL, "MySQL fatal error in create method");
            throw new DAOException("MySQL Error", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    /**
     * Get list of all categories
     * @return list
     * @throws DAOException custom exception
     */
    @Override
    public List<Category> getAll() throws DAOException {
        List<Category> categories = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SqlSynatx.SELECT_FROM_CATEGORY);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                categories.add(buildCategory(resultSet));
            }
        } catch (SQLException e) {
            logger.log(Level.FATAL, "MySQL fatal error in getAll method");
            throw new DAOException("MySQL Error", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return categories;
    }

    /**
     * Get category by category id
     * @param id id
     * @return category object
     * @throws DAOException custom exception
     */
    @Override
    public Category getById(int id) throws DAOException {
        Category category = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SqlSynatx.SELECT_FROM_CATEGORY_WHERE);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                category = buildCategory(resultSet);
            }
        } catch (SQLException e) {
            logger.log(Level.FATAL, "MySQL fatal error in getById method");
            throw new DAOException("MySQL Error", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return category;
    }

    /**
     * Update row in category table
     * @param category category
     * @throws DAOException custom exception
     */
    @Override
    public void update(Category category) throws DAOException {
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SqlSynatx.UPDATE_CATEGORY_SET_WHERE);
            preparedStatement.setString(1, category.getName());
            if (category.getCategory() != null) {
                preparedStatement.setInt(2, category.getCategory().getId());
            } else {
                preparedStatement.setInt(2, 0);
            }
            preparedStatement.setInt(3, category.getId());
            preparedStatement.executeUpdate();
            logger.log(Level.INFO, "Update Category: {} [{}]", category.getName(), category.getId());
        } catch (SQLException e) {
            logger.log(Level.FATAL, "MySQL fatal error in update method");
            throw new DAOException("MySQL Error", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    /**
     * Remove row from category table
     * @param id id
     * @throws DAOException custom exception
     */
    @Override
    public void remove(int id) throws DAOException {
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SqlSynatx.DELETE_FROM_CATEGORY_WHERE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            logger.log(Level.INFO, "Category [{}] removed", id);
        } catch (SQLException e) {
            logger.log(Level.FATAL, "MySQL fatal error in remove method");
            throw new DAOException("MySQL Error", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    /**
     * Get max id in category table
     * @return max id
     * @throws DAOException custom exception
     */
    @Override
    public int getMaxId() throws DAOException {
        int maxId = -1;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SqlSynatx.SELECT_MAX_FROM_CATEGORY);
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

    /**
     * Build category object
     * @param resultSet row
     * @return category object
     * @throws SQLException SQL exception
     * @throws DAOException custom exception
     */
    private Category buildCategory(ResultSet resultSet) throws SQLException, DAOException {
        int id = resultSet.getInt(SqlTables.CATEGORY_ID);
        String name = resultSet.getString(SqlTables.CATEGORY_NAME);
        Category category = null;
        if (resultSet.getInt(SqlTables.CATEGORY_CATEGORY_ID) != 0) {
            category = getById(resultSet.getInt(SqlTables.CATEGORY_CATEGORY_ID));
        }
        return EntityConstructor.buildCategory(id, name, category);
    }
}
