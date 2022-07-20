package com.example.demo_web.service;

import com.example.demo_web.config.MessageConfig;
import com.example.demo_web.model.*;
import com.example.demo_web.model.Auction;
import com.example.demo_web.repository.*;
import com.example.demo_web.request.AddAuctionRequest;
import com.example.demo_web.response.*;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
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
    UserServiceImpl userService;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TransactionServiceImpl transactionService;
    @Autowired
    BidsRepository bidsRepository;
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
            newauction.setStatus(0);
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

    @Override
    public void setWinner(Auction auction) {
        ArrayList<Bids>listBid = bidsRepository.findByAuctionBids(auction);
        int maxprice=0;
        Bids maxbid = new Bids();
        for(Bids bid :listBid){
            if(bid.getBid_price()>maxprice){
                maxprice=bid.getBid_price();
                maxbid=bid;
            }
        }
        transactionService.addTransaction(maxbid);
        auction.setWinner(maxbid.getUserBids());
        userService.updateBalanceUser(maxbid,maxprice);

    }

    @Override
    public void deleteItemAuction(Item item) {
     ArrayList<Auction> listAuction=  auctionRepository.listAuctionItemDelete(item.getId());
     for (Auction auction :listAuction){
         auction.setIsDelete(1);
         auctionRepository.save(auction);
        }
    }

    @Scheduled(fixedDelay = 1000 )
    public void checkStartStopAuction() {
        ArrayList<Auction> listAuction = (ArrayList<Auction>) auctionRepository.findAll();
        for(Auction auction :listAuction){
            LocalDateTime now = LocalDateTime.now();
            if(auction.getStatus()!=-1){
                if(now.isAfter(auction.getTimeStart())){
                    auction.setStatus(1);
                    auctionRepository.save(auction);
                }
                if(now.isAfter(auction.getTimeEnd())){
                    auction.setStatus(-1);
                    auctionRepository.save(auction);
                    setWinner(auction);
                }
            }
        }
    }
}
