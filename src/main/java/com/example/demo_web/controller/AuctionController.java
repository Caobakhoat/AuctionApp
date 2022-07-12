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
import java.util.ArrayList;
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
    public ResponseEntity getAllAuctionResponse(){
        GetAllAuctionResponse res = auctionServiceIpml.getAllAuction();
        return ResponseEntity.ok().body(res);
    }

    @PostMapping("/addAuction")
    public ResponseEntity addAuction(@RequestBody Auction newAuction){
        auctionServiceIpml.saveAuction(newAuction);
        AddAuctionResponse res = new AddAuctionResponse();
        res.setCode(1);
        res.setMessage("add Auction succeeded");
        res.setResult(newAuction);
        return ResponseEntity.ok().body(res);
    }

    @PutMapping("/updateAuction")
    public ResponseEntity updateAuction(@RequestBody Auction newAuction){
        auctionServiceIpml.saveAuction(newAuction);
        AddAuctionResponse res = new AddAuctionResponse();
        return ResponseEntity.ok().body(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAuction(@RequestBody Auction newAuction){
        DeleteAuctionResponse res = auctionServiceIpml.deleteAuction(newAuction);
        return ResponseEntity.ok().body(res);
    }
    @GetMapping("/findAuctionByName")
    public ResponseEntity findAuctionByName(@RequestParam String name){
        ArrayList<Auction> res = auctionServiceIpml.findAutionbyName(name);
        return ResponseEntity.ok().body(res);
    }
}
