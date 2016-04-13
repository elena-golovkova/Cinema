package com.cinema.service.api;

import com.cinema.dto.UserDTO;
import com.cinema.exception.UserLoginException;

public interface UserService extends Service<UserDTO, Integer> {

    UserDTO findUser(String login, String password);
    public void createUser(UserDTO userDTO) throws UserLoginException;

}
