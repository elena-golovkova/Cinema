package com.cinema.service.impl;

import com.cinema.Transformer;
import com.cinema.dao.api.UserDAO;
import com.cinema.dao.impl.UserDAODB;
import com.cinema.dao.impl.UserDAOImpl;
import com.cinema.dao.storage.Configuration;
import com.cinema.dto.UserDTO;
import com.cinema.exception.UserException;
import com.cinema.exception.UserLoginException;
import com.cinema.model.User;
import com.cinema.service.api.UserService;

import java.util.LinkedList;
import java.util.List;


public final class UserServiceImpl implements UserService {
    private static UserServiceImpl userService;
    boolean isBD = Boolean.valueOf(Configuration.getInstance().getProperty(Configuration.DB));

    public synchronized static UserServiceImpl getInstance() {
        if (userService == null) {
            userService = new UserServiceImpl();
        }
        return userService;
    }

    private UserServiceImpl() {
    }

    @Override
    public UserDTO findUser(String login, String password) {
        UserDTO userDTO = null;
        if (!isBD) {
            UserDAO userDAO = UserDAOImpl.getInstance();
            User user  = (User) userDAO.findUser(login, password);
            userDTO = Transformer.userToUserDto(user);
        } else {
            UserDAO userDAO = UserDAODB.getInstance();
            User user = (User) userDAO.findUser(login, password);
            userDTO = Transformer.userToUserDto(user);
        }
        return userDTO;
    }

    @Override
    public List<UserDTO> getAll() {
        List<UserDTO> usersDTO = new LinkedList<>();
        if (!isBD) {
            UserDAO userDAO = UserDAOImpl.getInstance();
            List<User> users = userDAO.getAll();
            usersDTO = Transformer.listUserToListUserDTO(users);

        } else {
            UserDAO userDAO = UserDAODB.getInstance();
            List<User> users = userDAO.getAll();
            usersDTO = Transformer.listUserToListUserDTO(users);
        }
        return usersDTO;
    }

    @Override
    public void create(UserDTO userDTO) {
        if (!isBD) {
            UserDAO userDAO = UserDAOImpl.getInstance();
            User user = Transformer.userDTOToUser(userDTO);
            userDAO.create(user);
        } else {
            UserDAO userDAO = UserDAODB.getInstance();
            User user = Transformer.userDTOToUser(userDTO);
            userDAO.create(user);
        }
    }

    @Override
    public UserDTO get(Integer id) {
        UserDTO userDTO = null;
        if (!isBD) {
            UserDAO userDAO = UserDAOImpl.getInstance();
            User user = (User) userDAO.get(id);
            userDTO = Transformer.userToUserDto(user);

        } else {
            UserDAO userDAO = UserDAODB.getInstance();
            User user = (User) userDAO.get(id);
            userDTO = Transformer.userToUserDto(user);
        }
        return userDTO;
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        UserDTO userDTONew = null;
        if (!isBD) {
            UserDAO userDAO = UserDAOImpl.getInstance();
            User user = (User) userDAO.update(Transformer.userDTOToUser(userDTO));
            userDTONew = Transformer.userToUserDto(user);
        } else {
            UserDAO userDAO = UserDAODB.getInstance();
            User user = (User) userDAO.update(Transformer.userDTOToUser(userDTO));
            userDTONew = Transformer.userToUserDto(user);
        }
        return userDTONew;
    }

    @Override
    public void delete(Integer id) {
        if (!isBD) {
            UserDAO userDAO = UserDAOImpl.getInstance();
            userDAO.delete(id);
        } else {
            UserDAO userDAO = UserDAODB.getInstance();
            userDAO.delete(id);
        }
    }
}
