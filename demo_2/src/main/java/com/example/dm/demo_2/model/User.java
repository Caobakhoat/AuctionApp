package com.example.dm.demo_2.model;
import com.example.dm.demo_2.model.Auction;
import com.example.dm.demo_2.model.Bid;

import javax.persistence.*;
import java.io.Serializable;

import java.util.List;

@Entity(name = "User")
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "DOB")
    private String DOB;

    @Column(name = "email")
    private String email;

    @Column(name = "balance")
    private int balance;

    @Column(name = "role")
    private String role;

    @Column(name = "create_at")
    private String create_at;

    @Column(name = "modify_at")
    private String modify_at;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Bid> listBidUser;
    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "auction")
    //private List<Auction> listAuctionCreated;

    public User() {
    }

    public User(int id, String username, String password, String name, String address, String DOB, String email, int balance, String role, String create_at, String modify_at, List<Bid> listBidUser, List<Auction> listAuctionCreated) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.DOB = DOB;
        this.email = email;
        this.balance = balance;
        this.role = role;
        this.create_at = create_at;
        this.modify_at = modify_at;
        this.listBidUser = listBidUser;
        //this.listAuctionCreated = listAuctionCreated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public String getModify_at() {
        return modify_at;
    }

    public void setModify_at(String modify_at) {
        this.modify_at = modify_at;
    }

    public List<Bid> getListBidUser() {
        return listBidUser;
    }

    public void setListBidUser(List<Bid> listBidUser) {
        this.listBidUser = listBidUser;
    }

//    public List<Auction> getListAuctionCreated() {
//        return listAuctionCreated;
//    }
//
//    public void setListAuctionCreated(List<Auction> listAuctionCreated) {
//        this.listAuctionCreated = listAuctionCreated;
//    }
}