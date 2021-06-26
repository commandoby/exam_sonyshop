package com.commandoby.sonyShop.dao;

import com.commandoby.sonyShop.exceptions.ConnectionPoolException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    //Singleton instance
    private static volatile ConnectionPool instance;

    //Configuration properties
    private static final String DB_PROPERTY_FILE = "application";
    private static final String DB_DRIVER_NAME = "db.driver.name";
    private static final String DB_URL = "db.url";
    private static final String DB_LOGIN = "db.login";
    private static final String DB_PASS = "db.pass";
    private static final int MAX_CONNECTION_COUNT = 10;
    private static final int MIN_CONNECTION_COUNT = 5;

    private static String url;
    private static String login;
    private static String pass;
    private static String driverName;

    static {
        ResourceBundle rb = ResourceBundle.getBundle(DB_PROPERTY_FILE);
        url = rb.getString(DB_URL);
        login = rb.getString(DB_LOGIN);
        pass = rb.getString(DB_PASS);
        driverName = rb.getString(DB_DRIVER_NAME);
    }

    private volatile int currentConnectionNumber = MIN_CONNECTION_COUNT;
    private BlockingQueue<Connection> pool = new ArrayBlockingQueue<>(MAX_CONNECTION_COUNT, true);

    //Singleton
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

    //Add new connection to queue in constructor
    private ConnectionPool() {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        for (int i = 0; i < MIN_CONNECTION_COUNT; i++) {
            try {
                pool.add(DriverManager.getConnection(url, login, pass));
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Connection getConnection() throws ConnectionPoolException {
        Connection connection;
        try {
            if (pool.isEmpty() && currentConnectionNumber < MAX_CONNECTION_COUNT) {
                openAdditionalConnection();

            }
            connection = pool.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ConnectionPoolException("Max count of connections was reached", e);
        }
        return connection;
    }

    public void closeConnection(Connection connection) throws ConnectionPoolException {
        if (connection != null) {
            if (currentConnectionNumber > MIN_CONNECTION_COUNT) {
                currentConnectionNumber--;
            }
            try {
                pool.put(connection);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new ConnectionPoolException("Connection wasn't returned to the pool properly", e);
            }
        }
    }

    private void openAdditionalConnection() throws ConnectionPoolException {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolException("DB driver wasn't found", e);
        }
        try {
            pool.add(DriverManager.getConnection(url, login, pass));
            currentConnectionNumber++;
        } catch (SQLException e) {
            throw new ConnectionPoolException("New connection wasn't added in the connection pool", e);
        }
    }
}

