package com.example.demo_web.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBidsRequest {
    private int bid_price;
    private String token;
    private int idAuction;
}

