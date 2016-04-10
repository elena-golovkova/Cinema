package com.cinema.dao.api;

import com.cinema.exception.UserException;
import com.cinema.exception.UserLoginException;
import com.cinema.model.User;
import java.util.List;

public interface UserDAO<User, Integer> extends Dao<User, Integer> {

      User findUser(String login, String password) throws UserException;
}
