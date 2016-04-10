package com.cinema.service.api;

import com.cinema.dto.UserDTO;

public interface UserService extends Service<UserDTO, Integer> {

    UserDTO findUser(String login, String password);

}
