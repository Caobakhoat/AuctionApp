package com.example.demo_web.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name= "Transaction")
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "paid")
    private int paid;
    @Column(name = "creatAt")
    private int creatAt;
    @Column(name = "modifyAt")
    private int modifyAt;
    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User userTransaction;
    @ManyToOne
    @JoinColumn(name = "idAuction", nullable = false)
    private Auction auctionTransaction;

    public Transaction() {
    }

    public Transaction(int id, int paid, int creatAt, int modifyAt, User userTransaction, Auction auctionTransaction) {
        this.id = id;
        this.paid = paid;
        this.creatAt = creatAt;
        this.modifyAt = modifyAt;
        this.userTransaction = userTransaction;
        this.auctionTransaction = auctionTransaction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
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

    public User getUserTransaction() {
        return userTransaction;
    }

    public void setUserTransaction(User userTransaction) {
        this.userTransaction = userTransaction;
    }

    public Auction getAuctionTransaction() {
        return auctionTransaction;
    }

    public void setAuctionTransaction(Auction auctionTransaction) {
        this.auctionTransaction = auctionTransaction;
    }
}
