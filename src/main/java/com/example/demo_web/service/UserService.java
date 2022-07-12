package com.example.demo_web.service;

import com.example.demo_web.model.User;
import com.example.demo_web.request.LoginRequest;
import com.example.demo_web.response.BaseResponse;
import com.example.demo_web.response.SaveUserResponse;
import org.springframework.security.web.savedrequest.SavedCookie;

public interface UserService  {
    BaseResponse checkLogin(LoginRequest req);
    User registerUser(User u);
    BaseResponse getAllUser ();
    BaseResponse checkLoginAdmin(LoginRequest req);
    SaveUserResponse saveUser(User u);
    BaseResponse deleteUser(User u);
}
