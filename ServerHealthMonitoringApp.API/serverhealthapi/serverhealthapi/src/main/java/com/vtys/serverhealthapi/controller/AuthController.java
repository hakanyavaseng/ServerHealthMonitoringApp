package com.vtys.serverhealthapi.controller;

import java.sql.Timestamp;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vtys.serverhealthapi.config.VerificationStatus;
import com.vtys.serverhealthapi.dto.LoginDto;
import com.vtys.serverhealthapi.dto.RegisterDto;
import com.vtys.serverhealthapi.dto.VerifyDto;
import com.vtys.serverhealthapi.entity.Roles;
import com.vtys.serverhealthapi.entity.UserVerificationCode;
import com.vtys.serverhealthapi.entity.Users;
import com.vtys.serverhealthapi.repo.RolesRepository;
import com.vtys.serverhealthapi.repo.UserRepository;
import com.vtys.serverhealthapi.repo.UserVerificationCodeRepository;
import com.vtys.serverhealthapi.service.EmailSenderService;
import java.sql.Types;

import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserVerificationCodeRepository userVerificationCodeRepository;
    private UserRepository userRepository;
    private RolesRepository rolesRepository;
    private PasswordEncoder passwordEncoder;
    private EmailSenderService emailSenderService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
            RolesRepository rolesRepository, PasswordEncoder passwordEncoder, EmailSenderService emailSenderService,
            UserVerificationCodeRepository userVerificationCodeRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailSenderService = emailSenderService;
        this.userVerificationCodeRepository = userVerificationCodeRepository;

    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {

        LocalDateTime currentDateTime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(currentDateTime);

        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }

        Users user = new Users();

        user.setUsername(registerDto.getUsername());
        user.setUserpassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setUseremail(registerDto.getUseremail());
        ;
        user.setUserlastlogin(timestamp.toString());
        user.setUserregistrationdate(Date.valueOf(LocalDate.now()).toString());
        user.setUserauthority("0");
        Roles roles = rolesRepository.findByRolename("USER").get();
        user.setRolesList(Collections.singletonList(roles));

        String verificationCodeString = generateVerificationCode();
        emailSenderService.sendVerificationEmail(registerDto.getUseremail(), verificationCodeString);

        userRepository.save(user);

        UserVerificationCode userVerificationCode = new UserVerificationCode();
        userVerificationCode.setUserid(user);
        userVerificationCode.setUsername(user.getUsername());
        userVerificationCode.setVerificationCode(verificationCodeString);

        userVerificationCodeRepository.save(userVerificationCode);

        return new ResponseEntity<>("User registered successfully, please verificate user.",
                org.springframework.http.HttpStatus.OK);

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {

        Users user = userRepository.findByUsername(loginDto.getUsername()).get();
        LocalDateTime currentDateTime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(currentDateTime);

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        user.setUserlastlogin(timestamp.toString());
        userRepository.save(user);

        return new ResponseEntity<>("User logged in successfully", org.springframework.http.HttpStatus.OK);

    }

    /*
     * @PostMapping("/googlelogin")
     * public ResponseEntity<String> googleLogin(@RequestBody GoogleLoginDto
     * googleLoginDto) {
     * 
     * if(userRepository.existsByUseremail(googleLoginDto.getEmail()))
     * {
     * return null;
     * }
     * else {
     * 
     * Users user = new Users();
     * String parsedUsername = googleLoginDto.getEmail().split("@")[0];
     * 
     * user.setUsername(parsedUsername);
     * user.setUseremail(googleLoginDto.getEmail());
     * user.setUserpassword("nopasswordthisisgooglelogin");
     * user.setUserregistrationdate(LocalDate.now().toString());
     * user.setUserlastlogin(LocalDate.now().toString());
     * 
     * userRepository.save(user);
     * 
     * }
     * return new ResponseEntity<>("User added to database by Google.",
     * org.springframework.http.HttpStatus.OK);
     * }
     * 
     */

        @PostMapping("/verify")
    public ResponseEntity<String> verify(@RequestBody VerifyDto verifyDto) {
        int verificationResultCode = verifyUserInDatabase(verifyDto.getUsername(), verifyDto.getVerificationCode());

        switch (verificationResultCode) {
            case VerificationStatus.SUCCESS:
                return new ResponseEntity<>("User verified successfully", HttpStatus.OK);
            case VerificationStatus.VERIFICATION_FAILED:
                return new ResponseEntity<>("Verification code is wrong", HttpStatus.BAD_REQUEST);
            case VerificationStatus.USER_NOT_FOUND:
                return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
            default:
                // Handle unexpected result
                return new ResponseEntity<>("API MESSAGE: Unexpected verification result", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private int verifyUserInDatabase(String username, String verificationCode) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;encrypt=true;trustServerCertificate=true;databaseName=HealthMonitorApp;user=sa;password=Sunucu123")) {
            try (CallableStatement statement = connection.prepareCall("{call sp_VerifyUser(?, ?, ?)}")) {
                statement.setString(1, username);
                statement.setString(2, verificationCode);
                statement.registerOutParameter(3, Types.INTEGER);

                statement.execute();

                // Retrieve the verification result code
                return statement.getInt(3);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return VerificationStatus.UNKNOWN_ERROR;
        } catch (Exception e) {
            e.printStackTrace();
            return VerificationStatus.UNKNOWN_ERROR;
        }
    }
     



    public static String generateVerificationCode() {
        Random random = new Random();
        int verificationCode = 1000 + random.nextInt(9000); // 1000-9999 range
        return String.valueOf(verificationCode);
    }

}
