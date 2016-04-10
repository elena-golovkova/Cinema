package com.cinema.datasource;

import com.cinema.dao.storage.Configuration;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {

    private static DataSource dataSource;
    private static ComboPooledDataSource pooledConnection;
    private static Connection connection;

    public synchronized static DataSource getInstance() {
        if (dataSource == null) {
            dataSource = new DataSource();
        }
        return dataSource;
    }

    private DataSource() {
        initPollConnections();
    }

    public synchronized Connection getConnection() {
        if (connection == null) {
            try {
                connection = pooledConnection.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    private static void initPollConnections() {
        if (pooledConnection == null) {
            pooledConnection = new ComboPooledDataSource();
            Configuration configuration = Configuration.getInstance();
            try {
                pooledConnection.setDriverClass(configuration.getProperty(Configuration.DRIVER_CLASS));
                pooledConnection.setJdbcUrl(configuration.getProperty(Configuration.JDBC_URL));
                pooledConnection.setUser(configuration.getProperty(Configuration.LOGIN));
                pooledConnection.setPassword(configuration.getProperty(Configuration.PASSWORD));
                pooledConnection.setMinPoolSize(5);
                pooledConnection.setAcquireIncrement(1);
                pooledConnection.setMaxPoolSize(100);
                pooledConnection.setMinPoolSize(1);
            } catch (PropertyVetoException e) {
                e.printStackTrace();
            }
        }

    }

}
