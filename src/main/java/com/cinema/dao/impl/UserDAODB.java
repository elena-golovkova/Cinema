package com.cinema.dao.impl;

import com.cinema.dao.api.UserDAO;
import com.cinema.exception.UserException;
import com.cinema.model.User;


public class UserDAODB extends CrudDAODataBase<User, Integer> implements UserDAO<User, Integer> {
    private static UserDAODB userDAO;

    public synchronized static UserDAODB getInstance() {

        if (userDAO == null) {
            userDAO = new UserDAODB(User.class);
        }
        return userDAO;
    }
    private UserDAODB(Class type) {
        super(type);
    }
//todo  findUser(String login, String password)
    @Override
    public User findUser(String login, String password) throws UserException {
        return null;
    }
}
