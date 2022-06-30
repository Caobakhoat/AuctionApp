package com.example.demo_web.model;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Auction")
public class Auction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "initPrice")
    private int initPrice;
    @Column(name = "curentPrice")
    private int curentPrice;
    @Column(name = "timeStart")
    private int timeStart;
    @Column(name = "timeEnd")
    private int timeEnd;
    @Column(name = "creatAt")
    private int creatAt;
    @Column(name = "modifyAt")
    private int modifyAt;
    @Column(name = "status")
    private int status;
    @OneToOne
    @JoinColumn(name = "idItem", nullable = false)
    private Item item;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "auctionBids")
    private List<Bids> listBid;
    @ManyToOne
    @JoinColumn(name = "idUserCreate", nullable = false)
    private User userCreatAuction;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "auctionTransaction")
    private List<Transaction> listTransaction;

    public Auction() {
    }

    public Auction(int id, int initPrice, int curentPrice, int timeStart, int timeEnd, int creatAt, int modifyAt, int status, Item item, List<Bids> listBid, User userCreatAuction, List<Transaction> listTransaction) {
        this.id = id;
        this.initPrice = initPrice;
        this.curentPrice = curentPrice;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.creatAt = creatAt;
        this.modifyAt = modifyAt;
        this.status = status;
        this.item = item;
        this.listBid = listBid;
        this.userCreatAuction = userCreatAuction;
        this.listTransaction = listTransaction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInitPrice() {
        return initPrice;
    }

    public void setInitPrice(int initPrice) {
        this.initPrice = initPrice;
    }

    public int getCurentPrice() {
        return curentPrice;
    }

    public void setCurentPrice(int curentPrice) {
        this.curentPrice = curentPrice;
    }

    public int getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(int timeStart) {
        this.timeStart = timeStart;
    }

    public int getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(int timeEnd) {
        this.timeEnd = timeEnd;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<Bids> getListBid() {
        return listBid;
    }

    public void setListBid(List<Bids> listBid) {
        this.listBid = listBid;
    }

    public User getUserCreatAuction() {
        return userCreatAuction;
    }

    public void setUserCreatAuction(User userCreatAuction) {
        this.userCreatAuction = userCreatAuction;
    }

    public List<Transaction> getListTransaction() {
        return listTransaction;
    }

    public void setListTransaction(List<Transaction> listTransaction) {
        this.listTransaction = listTransaction;
    }

    public Object[] toObject() {
        if (this.status == 0) {
            return new Object[]{
                this.id, this.item.getName(), "draft"
            };
        }
        if (this.status == 1) {
            return new Object[]{
                this.id, this.item.getName(), "live"
            };
        }
        return new Object[]{
            this.id, this.item.getName(), "Ended"
        };
    }

}
