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
    @Autowired
    MessageConfig messageConfig;

    @Override
    public AddAuctionResponse saveAuction(Auction auction) {
        AddAuctionResponse res = new AddAuctionResponse();
        res.setCode(messageConfig.CODE_SUCCESS);
        res.setMessage(messageConfig.MESSAGE_ADDAUCTION);
        res.setResult( auctionRepository.save(auction));
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
        res.setMessage(messageConfig.MESSAGE_GETALLAUCTION);
        res.setResult(list);
        return res;
    }


    @Override
    public DeleteAuctionResponse deleteAuction(Auction auction){
        DeleteAuctionResponse res = new DeleteAuctionResponse();
        res.setCode(messageConfig.CODE_SUCCESS);
        res.setMessage(messageConfig.MESSAGE_DELETEAUCTION);
        res.setResult(1);
        auctionRepository.save(auction);
        return res;
    }

    @Override
    public UpdateAuctionResponse updateAuction(Auction auction) {
        UpdateAuctionResponse res = new UpdateAuctionResponse();
        res.setCode(messageConfig.CODE_SUCCESS);
        res.setMessage(messageConfig.MESSAGE_UPDATEAUCTION);
        res.setResult(auctionRepository.save(auction));
        return res;
    }

    @Override
    public ArrayList<Auction> findAuctionbyName(String name) {
        return auctionRepository.searchAuction(name);
    }

}
