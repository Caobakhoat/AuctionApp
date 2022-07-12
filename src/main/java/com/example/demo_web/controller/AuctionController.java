package com.example.demo_web.controller;

import com.example.demo_web.model.Auction;
import com.example.demo_web.repository.AuctionRepository;
import com.example.demo_web.response.*;
import com.example.demo_web.service.AuctionServiceIpml;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequiredArgsConstructor
@RequestMapping("/auction")
public class AuctionController {
    @Autowired
    AuctionServiceIpml auctionServiceIpml;
    @GetMapping("/getAllAuctions")
    public GetAllAuctionResponse getAllAuctionResponse(){
        GetAllAuctionResponse res = auctionServiceIpml.getAllAuction();
        return res;
    }

    @PostMapping("/addAuction")
    public AddAuctionResponse addAuction(@RequestBody Auction newAuction){
        auctionServiceIpml.saveAuction(newAuction);
        AddAuctionResponse res = new AddAuctionResponse();
        res.setCode(1);
        res.setMessage("add Auction succeeded");
        res.setResult(newAuction);
        return res;
    }

    @PutMapping("/updateAuction")
    public AddAuctionResponse updateAuction(@RequestBody Auction newAuction){
        AddAuctionResponse res = auctionServiceIpml.saveAuction(newAuction);
        return res;
    }

    @DeleteMapping("/{id}")
    public DeleteAuctionResponse deleteAuction(@RequestBody Auction newAuction){
        DeleteAuctionResponse res = auctionServiceIpml.deleteAuction(newAuction);
        return res;
    }
}
