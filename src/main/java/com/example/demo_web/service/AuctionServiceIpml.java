package com.example.demo_web.service;

import com.example.demo_web.model.Auction;
import com.example.demo_web.model.Auction;
import com.example.demo_web.model.Item;
import com.example.demo_web.model.User;
import com.example.demo_web.repository.AuctionRepository;
import com.example.demo_web.repository.ItemRepository;
import com.example.demo_web.repository.UserRepository;
import com.example.demo_web.response.DeleteAuctionResponse;
import com.example.demo_web.response.GetAllAuctionResponse;
import com.example.demo_web.response.GetAuctionResponse;
import com.example.demo_web.response.UpdateAuctionResponse;
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
    UserRepository userRepository;
    ItemRepository itemRepository;

    @Override
    public Auction saveAuction(Auction auction) {
        Auction i= auctionRepository.save(auction);
        return i;
    }

    @Override
    public GetAllAuctionResponse getAllAuction() {
        ArrayList<Auction> list = (ArrayList<Auction>) auctionRepository.findAll();
        GetAllAuctionResponse res = new GetAllAuctionResponse();
        res.setCode(1);
        res.setMessage("get all Auction success");
        res.setResult(list);
        return res;
    }

    @Override
    public GetAuctionResponse getAuction(int id){
        Optional<Auction> resO = auctionRepository.findById(id);
        Auction res_ = resO.get();
        GetAuctionResponse res = new GetAuctionResponse();
        res.setCode(1);
        res.setMessage("get Auction "+id+" succeeded");
        res.setResult(res_);
        return res;
    }

    @Override
    public UpdateAuctionResponse updateAuction(Auction newAuction,int id) {
        Auction updatedAuction = auctionRepository.findById(id).map(Auction -> {
            Auction.setTimeStart(newAuction.getTimeStart());
            Auction.setTimeEnd(newAuction.getTimeEnd());
            //Auction.setCreate_at(foundAuction.getClass().);
            Auction.setInitPrice(newAuction.getInitPrice());
            Auction.setStatus(newAuction.getStatus());
            Auction.setModifyAt(LocalDateTime.now());
            Optional<User> optionalHost = userRepository.findById(id);
            User host = optionalHost.get();
            Auction.setUserCreatAuction(host);
            Optional<Item> optionalItem = itemRepository.findById(id);
            Item item = optionalItem.get();
            Auction.setItem(item);
            return auctionRepository.save(Auction);
        }).orElseGet(()->{
            newAuction.setId(id);
            return auctionRepository.save(newAuction);
        });
        UpdateAuctionResponse res = new UpdateAuctionResponse();
        res.setCode(1);
        res.setMessage("get Auction "+id+" succeeded");
        res.setResult(updatedAuction);
        return res;
    }

    @Override
    public DeleteAuctionResponse deleteAuction(int id){
        DeleteAuctionResponse res = new DeleteAuctionResponse();
        auctionRepository.deleteById(id);
        res.setCode(1);
        res.setMessage("Delete Auction "+id+" succeeded");
        res.setResult(null);
        return res;
    }


}
