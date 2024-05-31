package com.micro.fintech.service;
/*
@Author hakim a.k.a. Hakim Amarullah
Java Developer
Created on 5/28/2024 8:20 PM
@Last Modified 5/28/2024 8:20 PM
Version 1.0
*/


import com.micro.fintech.model.User;
import com.micro.fintech.model.dto.LoginDTO;
import com.micro.fintech.model.dto.PagedResponse;
import com.micro.fintech.model.response.ApiResponse;
import org.springframework.data.domain.Pageable;

public interface UserService {

   ApiResponse<User> registerUser(User user);

   ApiResponse<String> login(LoginDTO loginDTO);

   ApiResponse<Void> deleteUserByUsername(String username);

   ApiResponse<Void> updateUser(User user);

   ApiResponse<PagedResponse<User>> getAllActiveUsers(Pageable pageable);
}
