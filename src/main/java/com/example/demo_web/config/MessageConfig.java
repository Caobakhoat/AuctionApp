package com.example.demo_web.config;


import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {
    public final int CODE_SUCCESS =1;
    public final int CODE_FAILED =-1;
    public final String MESSGAGE_LOGINSUCCESS ="login success";
    public final String MESSGAGE_LOGINFAILED ="username or password incorrect";
    public final int CODE_UNAUTHOR_ADMIN =-2;
    public final String MESSGAGE_REGISTERSUCCESS="register success";
    public final String MESSGAGE_REGISTERFAILED=" username already exist";
    public final String MESSGAGE_GETALLUSER="get all user success";
    public final String MESSAGE_GETALLITEM="get all item success";
    public final String MESSAGE_ADDITEM="add item success";

}
