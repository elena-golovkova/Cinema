package com.cinema.controller.listener;

import com.cinema.datasource.DataSource;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;


public class ContextListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String[] sqlList = null;
        String sql = null;
        String sqlFile = "db\\schema.sql";

        sql = getFileWithUtil(sqlFile);

        if (sql != null) {

            sqlList = sql.split(";");

            DataSource instance = DataSource.getInstance();
            Connection connection = instance.getConnection();

            for (String query : sqlList)
                try {

                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }


    private String getFile(String fileName) {
        StringBuilder result = new StringBuilder("");
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }
            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();

    }

    private String getFileWithUtil(String fileName) {
        String result = "";
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            result = IOUtils.toString(classLoader.getResourceAsStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}

