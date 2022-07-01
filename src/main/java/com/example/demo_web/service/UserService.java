package com.example.demo_web.service;

import com.example.demo_web.request.LoginRequest;
import com.example.demo_web.request.RegisterRequest;
import com.example.demo_web.response.BaseResponse;
import org.springframework.stereotype.Service;

public interface UserService  {
    BaseResponse checkLogin(LoginRequest req);
    BaseResponse registerUser(RegisterRequest req);
}
