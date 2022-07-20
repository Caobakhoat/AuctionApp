package com.example.demo_web.service;

import com.example.demo_web.model.Bids;
import com.example.demo_web.model.User;
import com.example.demo_web.request.LoginRequest;
import com.example.demo_web.response.BaseResponse;
import com.example.demo_web.response.SetAdminResponse;

public interface UserService  {
    BaseResponse checkLogin(LoginRequest req);
    User registerUser(User u);
    BaseResponse getAllUser ();
    BaseResponse checkLoginAdmin(LoginRequest req);
    SetAdminResponse setAdmin(User u);
    void updateBalanceUser (Bids maxbid,int maxprice);

}
