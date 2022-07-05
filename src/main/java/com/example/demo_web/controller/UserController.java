package com.example.demo_web.controller;


import com.example.demo_web.request.LoginRequest;
import com.example.demo_web.request.RegisterRequest;
import com.example.demo_web.response.BaseResponse;
import com.example.demo_web.service.UserServiceImpl;
import com.example.demo_web.tokenAuthen.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceImpl userService;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping(value = "/login")
    public BaseResponse checklogin( @RequestBody LoginRequest req){
        System.out.println("cbk");
        BaseResponse res = new BaseResponse();
        res=userService.checkLogin(req);
        System.out.println(res);
        return res;
    }
    @PostMapping(value = "/register")
    public BaseResponse registerUser(@RequestBody RegisterRequest req){
        BaseResponse res = new BaseResponse();
        res=userService.registerUser(req);
        return res;
    }
    @GetMapping(value = "/getAllUser")
    public  BaseResponse getAllUser(){
        BaseResponse res = new BaseResponse();
        res= userService.getAllUser();
        return res;
    }

}
