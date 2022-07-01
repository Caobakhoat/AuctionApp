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
import java.util.ArrayList;
import java.util.List;


@Entity()
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "username",nullable = false)
    private String username;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "address",nullable = false)
    private String address;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "email",nullable = false)
    private String email;
    @Column(name = "dob",nullable = false)
    private String dob;
    @Column(name = "role",nullable = false)
    private String role;
    @Column(name = "balance",nullable = false)
    private int balance;
    @Column(name = "creatAt")
    @CreationTimestamp
    private LocalDateTime creatAt;
    @Column(name = "modifyAt")
    @UpdateTimestamp
    private LocalDateTime modifyAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userBids")
    private List<Bids> listBidUser;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userCreatAuction")
    private List<Auction> listAuctionCreated;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userTransaction")
    private List<Transaction> listTransactionUser;



}