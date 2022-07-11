package com.example.demo_web.service;

import com.example.demo_web.request.AddBidsRequest;
import com.example.demo_web.response.AddBidsResponse;

public interface BidsService {
    AddBidsResponse addBids (AddBidsRequest req);
}
