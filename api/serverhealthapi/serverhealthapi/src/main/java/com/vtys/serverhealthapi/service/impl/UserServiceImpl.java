package com.vtys.serverhealthapi.service.impl;

import com.vtys.serverhealthapi.dto.UserDto;
import com.vtys.serverhealthapi.entity.Users;
import com.vtys.serverhealthapi.repo.UserRepository;
import com.vtys.serverhealthapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    @Override
    public UserDto createUser(UserDto userDto) {

        Users user = modelMapper.map(userDto, Users.class);

        //Save user information
        user.setUsername(userDto.getUsername());
        user.setUserpassword(userDto.getUserpassword());
        user.setUseremail(userDto.getUseremail());
        user.setUserregistrationdate(userDto.getUserregistrationdate());
        user.setUserlastlogin(userDto.getUserlastlogin());

        final Users kDb = userRepository.save(user);
        user.setUserid(kDb.getUserid());
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<Users> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        for (Users user : users) {
            userDtos.add(modelMapper.map(user, UserDto.class));
        }
        return userDtos;

    }

   
}
