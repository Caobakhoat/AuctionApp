package com.example.demo_web.service;


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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

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

            User us = (User)authenticate.getPrincipal();
            String accessToken = jwtTokenUtil.generateAccessToken(user);
            String refreshToken = jwtTokenUtil.generateRefreshToken(user);
            user=userRepository.findByUsername(user.getUsername());
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("token",accessToken);
            map.put("user", user);
            map.put("refreshToken",refreshToken);
            res.setResult(map);
            res.setCode(1);
            res.setMessage("Login success");
            return res;

        }
        catch (BadCredentialsException ex) {
            res.setCode(-1);
            res.setMessage("login failed");
            return res;
        }
    }
    @Override
    public RegisterResponse registerUser(RegisterRequest req){
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
            newuser.setPassword(passwordEncoder.encode(req.getPassword()));
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
    public GetAllUserResponse getAllUser (){
        ArrayList<User> list = (ArrayList<User>) userRepository.findAll();
        GetAllUserResponse res = new GetAllUserResponse();
        res.setCode(1);
        res.setMessage("Get List User success");
        res.setResult(list);
        return res;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);

    }
}
