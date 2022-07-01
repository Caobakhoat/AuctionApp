package com.example.demo_web.controller;


import com.example.demo_web.model.User;
import com.example.demo_web.request.LoginRequest;
import com.example.demo_web.response.BaseResponse;
import com.example.demo_web.response.LoginResponse;
import com.example.demo_web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping(value = "/check-login", method=RequestMethod.POST)
    public String checklogin(LoginRequest req, Model model){
        BaseResponse res = new BaseResponse();
        res=userService.checkLogin(req);
        if(res.getCode()!=-1){
            model.addAttribute("response",res);
            return "auction.html";
        }
        else{
            model.addAttribute("response",res);
            return "redirect:/";
        }
    }
//    @GetMapping()


}
