package com.example.dm.demo_2.repository;

import com.example.dm.demo_2.model.Item;
import com.example.dm.demo_2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByName(String name);

}
