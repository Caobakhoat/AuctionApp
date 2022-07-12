package com.example.demo_web.service;

import com.example.demo_web.model.Auction;

import com.example.demo_web.response.*;


public interface AuctionService {
    AddAuctionResponse saveAuction(Auction auction);
    GetAllAuctionResponse getAllAuction();
    DeleteAuctionResponse deleteAuction(Auction auction);
}
