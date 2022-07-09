package com.example.demo_web.service;


import com.example.demo_web.config.MessageConfig;
import com.example.demo_web.model.User;
import com.example.demo_web.repository.UserRepository;
import com.example.demo_web.request.LoginRequest;
import com.example.demo_web.request.RegisterRequest;
import com.example.demo_web.response.GetAllUserResponse;
import com.example.demo_web.response.LoginResponse;
import com.example.demo_web.response.RegisterResponse;
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
    public RegisterResponse registerUser(RegisterRequest req){
        RegisterResponse res = new RegisterResponse();
        User user =userRepository.findByUsername(req.getUsername());
        if(user!=null){
            res.setCode(messageConfig.CODE_FAILED);
            res.setMessage(messageConfig.MESSGAGE_REGISTERFAILED);
            res.setResult(false);
            return res;
        }else{
            User newuser = new User();
            newuser.setAddress(req.getAddress());
            newuser.setBalance(req.getBalance());
            newuser.setDob(req.getDob());
            newuser.setEmail(req.getEmail());
            newuser.setPassword(passwordEncoder.encode(req.getPassword()));
            newuser.setRole("GUEST");
            newuser.setName(req.getName());
            newuser.setUsername(req.getUsername());
            userRepository.save(newuser);
            res.setCode(messageConfig.CODE_SUCCESS);
            res.setMessage(messageConfig.MESSGAGE_REGISTERSUCCESS);
            res.setResult(true);
            return res;
        }
    }
    @Override
    public GetAllUserResponse getAllUser (){
        ArrayList<User> list = (ArrayList<User>) userRepository.findAll();
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

            if(user.getRole()!="admin"){
                res.setCode(messageConfig.CODE_UNAUTHOR_ADMIN);
                res.setMessage(messageConfig.MESSGAGE_LOGINFAILED);
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
}
