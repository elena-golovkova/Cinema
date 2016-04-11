package com.cinema.web;

import com.cinema.dao.storage.LocalDatePersistenceConverter;
import com.cinema.dao.storage.LocalDateTimePersistenceConverter;

import java.beans.PropertyVetoException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TestClass {
    public static void main(String[] args) throws PropertyVetoException, SQLException {
    /*private static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS User (id INT NOT NULL AUTO_INCREMENT, name VARCHAR(45) NULL, age INT, PRIMARY KEY (id))";
    private static final String UPDATE_USER_TABLE = "ALTER TABLE User ADD age INTEGER ";
    private static final String ADD_AUTOINCREMENT_USER_TABLE = "ALTER TABLE user CHANGE COLUMN id id INT(11) NOT NULL AUTO_INCREMENT";
    private static final String INSERT_USER_1 = "INSERT INTO user (name, age) VALUES ('Anton', 27)";
    private static final String INSERT_USER_2 = "INSERT INTO user (name, age) VALUES ('Vitia', 30)";
    private static final String INSERT_USER_3 = "INSERT INTO user (name, age) VALUES ('Pavel', 33)";
    private static final String SELECT = "SELECT * FROM movie";

    public static void main(String[] args) throws PropertyVetoException, SQLException {
        DataSource instance = DataSource.getInstance();
        Connection conn = instance.getConnection();
        Statement statement = conn.createStatement();
       *//* boolean resultSet = statement.execute(CREATE_USER_TABLE);
        int count = statement.executeUpdate(INSERT_USER_1);
        int count2 = statement.executeUpdate(INSERT_USER_2);*//*
        ResultSet resultSet = statement.executeQuery(SELECT);
        while (resultSet.next()){
            System.out.println(resultSet.getInt(1));
            System.out.println(resultSet.getString(2));
        }
        conn.close();
    }*/
        LocalDateTimePersistenceConverter converter = new LocalDateTimePersistenceConverter();
        LocalDateTime lc = LocalDateTime.of(2002, 05, 29, 14,50);
        Timestamp ts = converter.convertToDatabaseColumn(lc);
        System.out.println(lc);
        System.out.println(ts);

    }

}

