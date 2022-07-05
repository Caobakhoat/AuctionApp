package com.example.demo_web.config;


import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageUserConfig {
    public final int CODE_SUCCESS =1;
    public final int CODE_FAILED =-1;
    public final String MESSGAGE_LOGINSUCCESS ="login success";
    public final String MESSGAGE_LOGINFAILED ="username or password incorrect";

    public final String MESSGAGE_REGISTERSUCCESS="register success";
    public final String MESSGAGE_REGISTERFAILED=" username already exist";
    public final String MESSGAGE_GETALLUSER="get all user success";

}
