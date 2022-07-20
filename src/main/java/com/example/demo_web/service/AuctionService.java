package com.example.demo_web.service;

import com.example.demo_web.model.Auction;

import com.example.demo_web.model.Item;
import com.example.demo_web.request.AddAuctionRequest;
import com.example.demo_web.response.*;

import java.io.IOException;
import java.util.ArrayList;


public interface AuctionService {
    AddAuctionResponse addAuction(AddAuctionRequest req);
    GetAllAuctionResponse getAllAuction();
    DeleteAuctionResponse deleteAuction(Auction auction);
    UpdateAuctionResponse updateAuction(Auction auction);
    ArrayList<Auction> findAuctionbyName(String name);

    void setWinner(Auction auction);
    void deleteItemAuction(Item item);
}
