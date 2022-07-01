package com.example.demo_web.controller;


import com.example.demo_web.model.User;
import com.example.demo_web.request.LoginRequest;
import com.example.demo_web.request.RegisterRequest;
import com.example.demo_web.response.BaseResponse;
import com.example.demo_web.response.LoginResponse;
import com.example.demo_web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping(value = "/login")
    public BaseResponse checklogin( @RequestBody LoginRequest req){
        BaseResponse res = new BaseResponse();
        res=userService.checkLogin(req);
        return res;
    }
    @PostMapping(value = "/register")
    public BaseResponse registerUser(@RequestBody RegisterRequest req){
        BaseResponse res = new BaseResponse();
        res=userService.registerUser(req);
        return res;
    }


}
