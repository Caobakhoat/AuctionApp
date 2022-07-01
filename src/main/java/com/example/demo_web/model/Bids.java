package com.example.demo_web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bids")
public class Bids implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "bid_price",nullable = false)
    private int bid_price;
    @Column(name = "creatAt")
    @CreatedBy
    private LocalDateTime creatAt;
    @Column(name = "modifyAt")
    @LastModifiedBy
    private LocalDateTime modifyAt;
    @ManyToOne
    @JoinColumn(name = "idAuction", nullable = false)
    private Auction auctionBids;
    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User userBids;


}