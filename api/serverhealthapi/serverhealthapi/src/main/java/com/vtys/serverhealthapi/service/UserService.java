package com.vtys.serverhealthapi.service;

import com.vtys.serverhealthapi.dto.UserDto;
import com.vtys.serverhealthapi.entity.Users;

import java.util.List;

public interface UserService {

    //Create user function signature
    UserDto createUser(UserDto userDto);

    List<UserDto> getAllUsers();


}
