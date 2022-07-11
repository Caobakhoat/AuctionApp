package com.example.demo_web.service;

import com.example.demo_web.model.Auction;

import com.example.demo_web.response.DeleteAuctionResponse;
import com.example.demo_web.response.GetAllAuctionResponse;
import com.example.demo_web.response.GetAuctionResponse;
import com.example.demo_web.response.UpdateAuctionResponse;


public interface AuctionService {
    Auction saveAuction(Auction auction);
    GetAllAuctionResponse getAllAuction();
    GetAuctionResponse getAuction(int id);
    UpdateAuctionResponse updateAuction(Auction auction, int id);
    DeleteAuctionResponse deleteAuction(int id);
}
