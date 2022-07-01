package com.example.dm.demo_2.repository;

import com.example.dm.demo_2.model.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AuctionRepository extends JpaRepository<Auction, Integer> {
    //List<Auction> findByName(String name);
}
