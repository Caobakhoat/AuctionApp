package com.example.demo_web.controller;


import com.example.demo_web.config.MessageConfig;
import com.example.demo_web.request.LoginRequest;
import com.example.demo_web.request.RegisterRequest;
import com.example.demo_web.response.BaseResponse;
import com.example.demo_web.service.UserServiceImpl;
import com.example.demo_web.tokenAuthen.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    private final MessageConfig messageConfig;

    @PostMapping(value = "/login")
    public ResponseEntity checklogin(@RequestBody LoginRequest req){
        BaseResponse res = new BaseResponse();
        res=userService.checkLogin(req);
        if(res.getCode()==messageConfig.CODE_FAILED){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
        }
        return ResponseEntity.ok().body(res);
    }
    @PostMapping(value = "/register")
    public ResponseEntity registerUser(@RequestBody RegisterRequest req){
        BaseResponse res = new BaseResponse();
        res=userService.registerUser(req);
        if(res.getCode()==messageConfig.CODE_FAILED){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
        }
        return ResponseEntity.ok().body(res);
    }

}
