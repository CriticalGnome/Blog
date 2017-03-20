package com.criticalgnome.blog.dao.utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Project Blog
 * Created on 20.03.2017.
 *
 * @author CriticalGnome
 */
public class ConnectionPool {
    private static volatile ConnectionPool instance;
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);

    private BlockingQueue<Connection> queue;
    private String url;
    private String user;
    private String password;
    private String driver;
    private int maxConnections;

    private ConnectionPool() {
        ResourceBundle bundle = ResourceBundle.getBundle("database");
        url = bundle.getString("url");
        user = bundle.getString("user");
        password = bundle.getString("password");
        driver = bundle.getString("driver");
        maxConnections = Integer.parseInt(bundle.getString("maxConnections"));
        queue = new ArrayBlockingQueue<>(maxConnections);
    }

    /**
     * Singleton
     */
    public static ConnectionPool getInstance() {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            }
        }
        return instance;
    }

    /**
     * Return connection
     * @return connection
     */
    public synchronized Connection getConnection() throws SQLException {
        Connection connection = null;
        if (queue.isEmpty()) {
            createConnection();
        }
        try {
            connection = queue.take();
        } catch (InterruptedException e) {
            logger.log(Level.FATAL, "ConnectionPool Queue error");
        }
        return connection;
    }

    /**
     * Add free connection to Queue
     * @param connection connection to be released
     */
    public synchronized void releaseConnection(Connection connection) {
        queue.add(connection);
    }

    /**
     * Create new connection
     */
    private void createConnection() throws SQLException {
        if (queue.size() < maxConnections) {
            try {
                Class.forName(driver);
                Connection connection = DriverManager.getConnection(url, user, password);
                queue.put(connection);

            } catch (ClassNotFoundException e) {
                logger.log(Level.FATAL, "Unexpected internal error");
            } catch (InterruptedException e) {
                logger.log(Level.FATAL, "ConnectionPool Queue error");
            }
        }
    }


}
