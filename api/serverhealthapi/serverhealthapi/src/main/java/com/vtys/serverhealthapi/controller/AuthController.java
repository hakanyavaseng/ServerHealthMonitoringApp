package com.vtys.serverhealthapi.controller;

import java.sql.Timestamp;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vtys.serverhealthapi.dto.LoginDto;
import com.vtys.serverhealthapi.dto.RegisterDto;
import com.vtys.serverhealthapi.entity.Roles;
import com.vtys.serverhealthapi.entity.Users;
import com.vtys.serverhealthapi.repo.RolesRepository;
import com.vtys.serverhealthapi.repo.UserRepository;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RolesRepository rolesRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
            RolesRepository rolesRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){

        LocalDateTime currentDateTime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(currentDateTime);


        if(userRepository.existsByUsername(registerDto.getUsername())){
            return ResponseEntity.badRequest().body("Username is already taken");
        }

        Users user = new Users();

        user.setUsername(registerDto.getUsername());
        user.setUserpassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setUseremail(registerDto.getUseremail());;
        //user.setUserlastlogin(timestamp.toString());
        user.setUserregistrationdate(Date.valueOf(LocalDate.now()).toString());
        Roles roles = rolesRepository.findByRolename("USER").get();
        user.setRolesList(Collections.singletonList(roles));

        userRepository.save(user);
        return new ResponseEntity<>("User registered successfully", org.springframework.http.HttpStatus.OK);

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){

        Users user = userRepository.findByUsername(loginDto.getUsername()).get();
        LocalDateTime currentDateTime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(currentDateTime);

       

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
     user.setUserlastlogin(timestamp.toString());
       userRepository.save(user);

        return new ResponseEntity<>("User logged in successfully", org.springframework.http.HttpStatus.OK);
        
       

    }


    
}
