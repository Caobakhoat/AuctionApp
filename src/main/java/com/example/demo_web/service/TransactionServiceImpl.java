package com.example.demo_web.service;

import com.example.demo_web.model.Bids;
import com.example.demo_web.model.Transaction;
import com.example.demo_web.repository.AuctionRepository;
import com.example.demo_web.repository.TransactionRepository;
import com.example.demo_web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    TransactionRepository transactionRepository;
    @Override
    public void addTransaction(Bids maxbid) {
        Transaction transaction = new Transaction();
        transaction.setPaid(maxbid.getBid_price());
        transaction.setUserTransaction(maxbid.getUserBids());
        transaction.setAuctionTransaction(maxbid.getAuctionBids());
        transactionRepository.save(transaction);
    }
}
