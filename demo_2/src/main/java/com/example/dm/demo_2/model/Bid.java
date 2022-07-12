package com.example.dm.demo_2.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="Bid")
@Table(name = "bids")
public class Bid implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "bid_price")
    private int bid_price;

    @Column(name = "create_at")
    private String create_at;

    @Column(name = "modify_at")
    private String modify_at;

    @ManyToOne
    @JoinColumn(name = "auction_id", nullable = false)
    private Auction auction;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Bid() {
    }

    public Bid(int id, int bid_price, String create_at, String modify_at, Auction auction, User user) {
        this.id = id;
        this.bid_price = bid_price;
        this.create_at = create_at;
        this.modify_at = modify_at;
        this.auction = auction;
        this.user = user;
    }
}