package com.example.demo_web.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "User")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "address")
    private String address;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "dob")
    private String dob;
    @Column(name = "role")
    private String role;
    @Column(name = "balance")
    private int balance;
    @Column(name = "creatAt")
    private int creatAt;
    @Column(name = "modifyAt")
    private int modifyAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userBids")
    private List<Bids> listBidUser;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userCreatAuction")
    private List<Auction> listAuctionCreated;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userTransaction")
    private List<Transaction> listTransaction;

    public User(int id, String username, String password, String address, String name, String email, String dob, String role, int balance, int creatAt, int modifyAt, List<Bids> listBidUser, List<Auction> listAuctionCreated, List<Transaction> listTransaction) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.address = address;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.role = role;
        this.balance = balance;
        this.creatAt = creatAt;
        this.modifyAt = modifyAt;
        this.listBidUser = listBidUser;
        this.listAuctionCreated = listAuctionCreated;
        this.listTransaction = listTransaction;
    }

    public User() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
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

    public List<Bids> getListBidUser() {
        return listBidUser;
    }

    public void setListBidUser(List<Bids> listBidUser) {
        this.listBidUser = listBidUser;
    }

    public List<Auction> getListAuctionCreated() {
        return listAuctionCreated;
    }

    public void setListAuctionCreated(List<Auction> listAuctionCreated) {
        this.listAuctionCreated = listAuctionCreated;
    }

    public List<Transaction> getListTransaction() {
        return listTransaction;
    }

    public void setListTransaction(List<Transaction> listTransaction) {
        this.listTransaction = listTransaction;
    }
}
