package com.example.demo_web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "auctions")
public class Auction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "initPrice",nullable = false)
    private int initPrice;
    @Column(name = "curentPrice",nullable = false)
    private int curentPrice;
    @Column(name = "timeStart",nullable = false)
    private LocalDateTime timeStart;
    @Column(name = "timeEnd",nullable = false)
    private LocalDateTime timeEnd;
    @Column(name = "creatAt")
    @CreationTimestamp
    private LocalDateTime creatAt;
    @Column(name = "modifyAt")
    @UpdateTimestamp
    private LocalDateTime modifyAt;
    @Column(name = "status")
    private int status;
    @OneToOne
    @JoinColumn(name = "idItem", nullable = false)
    private Item item;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "auctionBids")
    private List<Bids> listBid;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "auctionTransaction")
    private List<Transaction> listTransaction;
    @ManyToOne
    @JoinColumn(name = "idUserCreate", nullable = false)
    private User userCreatAuction;


}
