package com.example.demo_web.service;

import com.example.demo_web.config.MessageConfig;
import com.example.demo_web.model.Auction;
import com.example.demo_web.model.Auction;
import com.example.demo_web.model.Item;
import com.example.demo_web.model.User;
import com.example.demo_web.repository.AuctionRepository;
import com.example.demo_web.repository.ItemRepository;
import com.example.demo_web.repository.UserRepository;
import com.example.demo_web.request.AddAuctionRequest;
import com.example.demo_web.response.*;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuctionServiceIpml implements AuctionService{
    @Autowired
    AuctionRepository auctionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    MessageConfig messageConfig;

    @Override
    public AddAuctionResponse addAuction(AddAuctionRequest req)  {
        AddAuctionResponse res = new AddAuctionResponse();
        Auction newauction = new Auction();
        try {
            newauction.setTimeStart(req.getTimeStart());
            newauction.setCurentPrice(req.getInitPrice());
            newauction.setInitPrice(req.getInitPrice());
            newauction.setStatus(req.getStatus());
            newauction.setIsDelete(0);
            newauction.setTimeEnd(req.getTimeEnd());
            User user = userRepository.findById(req.getIdUser()).orElse(null);
            Item item = itemRepository.findById(req.getIdItem()).orElse(null);
            newauction.setUserCreatAuction(user);
            newauction.setItem(item);
            ArrayList<Auction>listAuction =(ArrayList<Auction>) auctionRepository.findAll();
            for(Auction auction :listAuction){
                if(auction.getItem().getId()== item.getId()){
                    res.setCode(messageConfig.CODE_FAILED);
                    res.setMessage(messageConfig.MESSAGE_ADDAUCTIONFAIL);
                    return res;
                }
            }
            res.setCode(messageConfig.CODE_SUCCESS);
            res.setMessage(messageConfig.MESSAGE_ADDAUCTIONSUCCES);
            res.setResult( auctionRepository.save(newauction));
            user.getListAuctionCreated().add(newauction) ;
            userRepository.save(user) ;
            return res;
        }catch (Exception e){
            res.setCode(messageConfig.CODE_FAILED);
            res.setMessage(messageConfig.MESSAGE_ADDAUCTIONERROR);
            return res;
        }
    }

    @Override
    public GetAllAuctionResponse getAllAuction() {
        GetAllAuctionResponse res = new GetAllAuctionResponse();
        ArrayList<Auction> listAuction = (ArrayList<Auction>) auctionRepository.findAll();
        for (Auction auction:listAuction){
            if (auction.getIsDelete()==1) listAuction.remove(auction);
        }
        res.setCode(messageConfig.CODE_SUCCESS);
        res.setMessage(messageConfig.MESSAGE_GETALLAUCTION);
        res.setResult(listAuction);
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
