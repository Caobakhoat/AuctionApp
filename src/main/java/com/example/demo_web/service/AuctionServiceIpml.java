package com.example.demo_web.service;

import com.example.demo_web.config.MessageConfig;
import com.example.demo_web.model.Auction;
import com.example.demo_web.model.Auction;
import com.example.demo_web.model.Item;
import com.example.demo_web.model.User;
import com.example.demo_web.repository.AuctionRepository;
import com.example.demo_web.repository.ItemRepository;
import com.example.demo_web.repository.UserRepository;
import com.example.demo_web.response.*;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class AuctionServiceIpml implements AuctionService{
    @Autowired
    AuctionRepository auctionRepository;
    MessageConfig messageConfig;

    @Override
    public AddAuctionResponse saveAuction(Auction auction) {
        Auction i= auctionRepository.save(auction);
        AddAuctionResponse res = new AddAuctionResponse();
        res.setCode(messageConfig.CODE_SUCCESS);
        res.setMessage("get all Auction success");
        res.setResult(i);
        return res;
    }

    @Override
    public GetAllAuctionResponse getAllAuction() {
        ArrayList<Auction> list = (ArrayList<Auction>) auctionRepository.findAll();
        for (Auction i:list){
            if (i.getIsDelete()==1) list.remove(i);
        }
        GetAllAuctionResponse res = new GetAllAuctionResponse();
        res.setCode(messageConfig.CODE_SUCCESS);
        res.setMessage("get all Auction success");
        res.setResult(list);
        return res;
    }


    @Override
    public DeleteAuctionResponse deleteAuction(Auction newAuction){
        DeleteAuctionResponse res = new DeleteAuctionResponse();
        newAuction.setIsDelete(1);
        res.setCode(messageConfig.CODE_SUCCESS);
        res.setMessage("Delete Auction "+newAuction.getId()+" succeeded");
        res.setResult(1);
        auctionRepository.save(newAuction);
        return res;
    }


}
