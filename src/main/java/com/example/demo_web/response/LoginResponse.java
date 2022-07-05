package com.example.demo_web.response;

import com.example.demo_web.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse extends BaseResponse{
    private Map<String,Object> result;

}
