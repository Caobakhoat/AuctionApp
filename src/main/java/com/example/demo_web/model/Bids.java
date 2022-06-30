package com.example.demo_web.model;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author bakhoat
 */
@Entity
@Table(name = "Bids")
public class Bids implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "amount")
    private int amount;
    @Column(name = "creatAt")
    private int creatAt;
    @Column(name = "modifyAt")
    private int modifyAt;
    
    
    @ManyToOne
    @JoinColumn(name = "idAuction",nullable = false)
    private Auction auctionBids;
    @ManyToOne
    @JoinColumn(name = "idUser",nullable = false)
    private User userBids;

    public Bids() {
    }

    public Bids(int id, int amount, int creatAt, int modifyAt, Auction auctionBids, User userBids) {
        this.id = id;
        this.amount = amount;
        this.creatAt = creatAt;
        this.modifyAt = modifyAt;
        this.auctionBids = auctionBids;
        this.userBids = userBids;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(int creatAt) {
        this.creatAt = creatAt;
    }

    public int getModifyAt() {
        return modifyAt;
    }

    public void setModifyAt(int modifyAt) {
        this.modifyAt = modifyAt;
    }

    public Auction getAuctionBids() {
        return auctionBids;
    }

    public void setAuctionBids(Auction auctionBids) {
        this.auctionBids = auctionBids;
    }

    public User getUserBids() {
        return userBids;
    }

    public void setUserBids(User userBids) {
        this.userBids = userBids;
    }

    


    
    
    
 }