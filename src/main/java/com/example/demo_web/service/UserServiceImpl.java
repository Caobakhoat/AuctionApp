package com.example.demo_web.service;


import com.example.demo_web.config.MessageConfig;
import com.example.demo_web.model.Auction;
import com.example.demo_web.model.User;
import com.example.demo_web.repository.UserRepository;
import com.example.demo_web.request.LoginRequest;
import com.example.demo_web.response.*;
import com.example.demo_web.tokenAuthen.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;


@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;
    @Autowired
    private final MessageConfig messageConfig;



    @Override
    public LoginResponse checkLogin(LoginRequest req) {
        LoginResponse res = new LoginResponse();
        try {
            User user = new User();
            user.setUsername(req.getUsername());
            user.setPassword(req.getPassword());
            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    user.getUsername(), user.getPassword()
                            )
                    );
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            String accessToken = jwtTokenUtil.generateAccessToken(user);
            user=userRepository.findByUsername(user.getUsername());
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("token",accessToken);
            map.put("user", user);
            res.setResult(map);
            res.setCode(messageConfig.CODE_SUCCESS);
            res.setMessage(messageConfig.MESSGAGE_LOGINSUCCESS);
            return res;

        }
        catch (BadCredentialsException ex) {
            res.setCode(messageConfig.CODE_FAILED);
            res.setMessage(messageConfig.MESSGAGE_LOGINFAILED);
            return res;
        }
    }
    @Override
    public User registerUser(User u){
        User user =userRepository.findByUsername(u.getUsername());
        if(user!=null){
            return null;
        }else{
            u.setPassword(passwordEncoder.encode(u.getPassword()));
            u.setRole("guest");
            return userRepository.save(u);
        }
    }
    @Override
    public GetAllUserResponse getAllUser (){
        ArrayList<User> list = (ArrayList<User>) userRepository.findAll();
        for (User i:list){
            if (i.getIsDelete()==1) list.remove(i);
        }
        GetAllUserResponse res = new GetAllUserResponse();
        res.setCode(messageConfig.CODE_SUCCESS);
        res.setMessage(messageConfig.MESSGAGE_GETALLUSER);
        res.setResult(list);
        return res;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      return userRepository.findByUsername(username);
    }

    @Override
    public LoginResponse checkLoginAdmin(LoginRequest req) {
        LoginResponse res = new LoginResponse();
        try {
            User user = new User();
            user.setUsername(req.getUsername());
            user.setPassword(req.getPassword());
            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    user.getUsername(), user.getPassword()
                            )
                    );
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            String accessToken = jwtTokenUtil.generateAccessToken(user);
            user = userRepository.findByUsername(user.getUsername());
            System.out.println(user.getRole());
            if(!user.getRole().equals("admin")){
                res.setCode(messageConfig.CODE_UNAUTHOR_ADMIN);
                res.setMessage(messageConfig.MESSGAGE_LOGINADMINFAILED);
                return res;
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("token", accessToken);
            map.put("user", user);
            res.setResult(map);
            res.setCode(messageConfig.CODE_SUCCESS);
            res.setMessage(messageConfig.MESSGAGE_LOGINSUCCESS);
            return res;

        } catch (BadCredentialsException ex) {
            res.setCode(messageConfig.CODE_FAILED);
            res.setMessage(messageConfig.MESSGAGE_LOGINFAILED);
            return res;
        }
    }

    @Override
    public SaveUserResponse saveUser(User u) {
        User i= userRepository.save(u);
        SaveUserResponse res = new SaveUserResponse();
        res.setCode(messageConfig.CODE_SUCCESS);
        res.setMessage("Saved");
        res.setResult(i);
        return res;
    }

    @Override
    public BaseResponse deleteUser(User u) {
        BaseResponse res = new BaseResponse();
        u.setIsDelete(1);
        res.setCode(messageConfig.CODE_SUCCESS);
        res.setMessage("Delete Auction "+u.getId()+" succeeded");
        userRepository.save(u);
        return res;
    }
}
