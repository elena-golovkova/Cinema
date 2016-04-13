package com.cinema.dao.impl;

import com.cinema.dao.api.UserDAO;
import com.cinema.exception.UserException;
import com.cinema.exception.UserLoginException;
import com.cinema.model.Role;
import com.cinema.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


public class UserDAODB extends CrudDAODataBase<User, Integer> implements UserDAO<User, Integer> {
    private static UserDAODB userDAO;
    private static final String FIND_USER = "Select * from user where login = ? and password = ?";
    private static final String FIND_USER_LOGIN = "Select * from user where login = ? ";

    public synchronized static UserDAODB getInstance() {

        if (userDAO == null) {
            userDAO = new UserDAODB(User.class);
        }
        return userDAO;
    }

    private UserDAODB(Class type) {
        super(type);
    }


    @Override
    public User findUser(String login, String password) {
        Connection connection = instance.getConnection();
        PreparedStatement preparedStatement = null;
        User user = null;

        try {
            preparedStatement = connection.prepareStatement(FIND_USER);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                LocalDate ld = converter.convertToEntityAttribute(resultSet.getDate("date"));
                Role role = Role.valueOf(resultSet.getString("role"));
                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setDate(ld);
                user.setRole(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void checkUser(String login) throws UserLoginException {
        boolean check = true;
        Connection connection = instance.getConnection();
        PreparedStatement preparedStatement = null;
        User user = null;

        try {
            preparedStatement = connection.prepareStatement(FIND_USER_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                LocalDate ld = converter.convertToEntityAttribute(resultSet.getDate("date"));
                Role role = Role.valueOf(resultSet.getString("role"));
                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setDate(ld);
                user.setRole(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (user != null) throw new UserLoginException("Login");

    }

    public void createUser(User entity) throws UserLoginException {
        userDAO.checkUser(entity.getLogin());
        super.create(entity);
    }
}
