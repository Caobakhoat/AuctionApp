package com.example.demo_web.service;


import com.example.demo_web.model.User;
import com.example.demo_web.repository.UserRepository;
import com.example.demo_web.request.LoginRequest;
import com.example.demo_web.request.RegisterRequest;
import com.example.demo_web.response.BaseResponse;
import com.example.demo_web.response.LoginResponse;
import com.example.demo_web.response.RegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public LoginResponse checkLogin(LoginRequest req) {
        LoginResponse res = new LoginResponse();
        User user = userRepository.findByUsername(req.getUsername());
        if(user==null){
            res.setCode(-1);
            res.setMessage("user exists");
            return res;
        }else{
            if(user.getPassword().equals(req.getPassword())){
                res.setCode(1);
                res.setMessage("sucsess");
                res.setUser(user);
                return res;
            }else{
                res.setCode(-1);
                res.setMessage("error password");
                return res;
            }
        }
    }
    @Override
    public RegisterResponse registerUser(RegisterRequest req){
        System.out.println(req);
        RegisterResponse res = new RegisterResponse();
        User user =userRepository.findByUsername(req.getUsername());
        if(user!=null){
            res.setCode(-1);
            res.setMessage("register faild");
            res.setResult(false);
            return res;
        }else{
            User newuser = new User();
            newuser.setAddress(req.getAddress());
            newuser.setBalance(req.getBalance());
            newuser.setDob(req.getDob());
            newuser.setEmail(req.getEmail());
            newuser.setPassword(req.getPassword());
            newuser.setRole("guest");
            newuser.setName(req.getName());
            newuser.setUsername(req.getUsername());
            userRepository.save(newuser);
            res.setCode(1);
            res.setMessage("register succsess");
            res.setResult(true);
            return res;
        }
    }
}
