package com.cinema.controller.listener;

import com.cinema.datasource.DataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ContextListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String[] sqlList = null;
        String sql = null;
        System.out.println("Context is initialised");
        String fileName = "E:\\java\\Cinema\\src\\main\\webapp\\sql\\schema.sql";
        try {
            sql = new String(Files.readAllBytes(Paths.get(fileName)));

        } catch (IOException e) {
            e.printStackTrace();
        }
        //Относительный путь не работает
       /* InputStream str = getClass().getResourceAsStream("WEB-INF/sql/schema.sql");
        sql = readStream(str);*/
        if (sql != null) {

            sqlList = sql.split(";");

            DataSource instance = DataSource.getInstance();
            Connection connection = instance.getConnection();

            for (String query : sqlList)
                try {
                    System.out.println(query);
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Context is destroyed");
    }

    protected String readStream(InputStream stream) {
        BufferedInputStream bis = new BufferedInputStream(stream);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        try {
            int result = bis.read();
            while (result != -1) {
                byte b = (byte) result;
                buf.write(b);
                result = bis.read();
            }
        } catch (IOException e) {
            return null;
        } finally {
            return buf.toString();
        }

    }
}
