package com.example.efabackend.service.impl;

import com.example.efabackend.Dto.LoginDto;
import com.example.efabackend.Dto.RegisterDto;
import com.example.efabackend.response.LoginResponse;
import com.example.efabackend.response.RegisterResponse;

public interface userService {

    RegisterResponse addUser(RegisterDto registerDTO);
    LoginResponse loginUser (LoginDto loginDto);
    long countByRole(String role);

}