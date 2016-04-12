package com.cinema.dao.impl;

import com.cinema.dao.api.UserDAO;
import com.cinema.dao.storage.InMemoryDB;
import com.cinema.exception.UserException;
import com.cinema.exception.UserLoginException;
import com.cinema.model.User;


public class UserDAOImpl extends AbstractDAOInMemory<User, Integer> implements UserDAO<User, Integer> {
    private static UserDAOImpl userDAO;

    public synchronized static UserDAOImpl getInstance() {

        if (userDAO == null) {
            userDAO = new UserDAOImpl(User.class);
        }
        return userDAO;
    }
    private UserDAOImpl(Class type) {
        super(type);
    }

    @Override
    public User findUser(String login, String password) {
        try {
            InMemoryDB instance = InMemoryDB.getInstance();
            User user = instance.findUser(login, password);
            return user;
        } catch (UserException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean checkUser(String login) throws UserLoginException {
        return false;
    }
}
