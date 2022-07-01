package com.example.demo_web.response;

import com.example.demo_web.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse extends BaseResponse{
    private User user;

}
