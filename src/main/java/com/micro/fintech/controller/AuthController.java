package com.micro.fintech.controller;
/*
@Author hakim a.k.a. Hakim Amarullah
Java Developer
Created on 5/28/2024 8:13 PM
@Last Modified 5/28/2024 8:13 PM
Version 1.0
*/

import com.fasterxml.jackson.databind.ObjectMapper;
import com.micro.fintech.model.User;
import com.micro.fintech.model.dto.LoginDTO;
import com.micro.fintech.model.dto.RegisterDTO;
import com.micro.fintech.model.response.ApiResponse;
import com.micro.fintech.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    private final ObjectMapper objectMapper;

    @Autowired
    public AuthController(UserService userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }



    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody @Valid LoginDTO loginDTO) {
        var response = userService.login(loginDTO);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getCode()));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> register(@RequestBody @Valid RegisterDTO registerDTO) {
        User user = objectMapper.convertValue(registerDTO, User.class);
        var response = userService.registerUser(user);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getCode()));
    }

}
