package com.example.demo_web.repository;

import com.example.demo_web.model.Bids;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidsRepository extends JpaRepository<Bids, Integer> {
}
