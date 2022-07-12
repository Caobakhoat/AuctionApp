package com.example.dm.demo_2.repository;

import com.example.dm.demo_2.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BidRepository extends JpaRepository<Item, Integer> {
    List<Item> findByName(String name);

}
