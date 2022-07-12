package com.example.demo_web.service;

import com.example.demo_web.model.Auction;

import com.example.demo_web.response.*;

import java.util.ArrayList;


public interface AuctionService {
    AddAuctionResponse saveAuction(Auction auction);
    GetAllAuctionResponse getAllAuction();
    DeleteAuctionResponse deleteAuction(Auction auction);

    ArrayList<Auction> findAutionbyName(String name);
}
