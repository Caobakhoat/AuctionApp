package com.example.demo_web.controller;

import com.example.demo_web.request.AddBidsRequest;
import com.example.demo_web.response.AddBidsResponse;
import com.example.demo_web.service.BidsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequiredArgsConstructor
@RequestMapping("/bids")
public class BidsController {
    @Autowired
    BidsServiceImpl bidsServiceImpl;

    @PostMapping(value = "/add")
    public ResponseEntity addBids(@RequestParam int bids_price,@RequestParam int idAuction,@RequestHeader("Authorization") String header){
        AddBidsResponse res = new AddBidsResponse();
        String token = header.split(" ")[1].trim();
        AddBidsRequest req = new AddBidsRequest(bids_price,token,idAuction);
        res= bidsServiceImpl.addBids(req);
        return ResponseEntity.ok().body(res);

    }
}
