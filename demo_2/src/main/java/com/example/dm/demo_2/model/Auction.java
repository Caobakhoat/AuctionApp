package com.example.dm.demo_2.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "Auction")
@Table(name = "auctions")
public class Auction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "start_at")
    private String start_at;

    @Column(name = "end_at")
    private String end_at;

    @Column(name = "initPrice")
    private int initPrice;

    @Column(name = "currentPrice")
    private int currentPrice;

    @Column(name = "status")
    private int status;

    @Column(name = "create_at")
    private String create_at;

    @Column(name = "modify_at")
    private String modify_at;

    @OneToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "auction")
    private List<Bid> listBid;

    @ManyToOne
    @JoinColumn(name = "host_id", nullable = false)
    private User host;

    public Auction() {
    }

    public Auction(int id, String start_at, String end_at, int initPrice, int currentPrice, int status, String create_at, String modify_at, Item item, List<Bid> listBid, User host) {
        this.id = id;
        this.start_at = start_at;
        this.end_at = end_at;
        this.initPrice = initPrice;
        this.currentPrice = currentPrice;
        this.status = status;
        this.create_at = create_at;
        this.modify_at = modify_at;
        this.item = item;
        this.listBid = listBid;
        this.host = host;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStart_at() {
        return start_at;
    }

    public void setStart_at(String start_at) {
        this.start_at = start_at;
    }

    public int getInitPrice() {
        return initPrice;
    }

    public void setInitPrice(int initPrice) {
        this.initPrice = initPrice;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getModify_at() {
        return modify_at;
    }

    public void setModify_at(String modify_at) {
        this.modify_at = modify_at;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<Bid> getListBid() {
        return listBid;
    }

    public void setListBid(List<Bid> listBid) {
        this.listBid = listBid;
    }

    public String getEnd_at() {
        return end_at;
    }

    public void setEnd_at(String end_at) {
        this.end_at = end_at;
    }

    public User getHost() {
        return host;
    }

    public void setHost(User host) {
        this.host = host;
    }
}