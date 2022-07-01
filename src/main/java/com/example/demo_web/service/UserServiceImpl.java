package com.example.demo_web.service;


import com.example.demo_web.model.User;
import com.example.demo_web.repository.UserRepository;
import com.example.demo_web.request.LoginRequest;
import com.example.demo_web.response.BaseResponse;
import com.example.demo_web.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
