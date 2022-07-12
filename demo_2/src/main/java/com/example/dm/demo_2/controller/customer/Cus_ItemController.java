package com.example.dm.demo_2.controller.customer;

import com.example.dm.demo_2.model.Item;
import com.example.dm.demo_2.model.ResObject;
import com.example.dm.demo_2.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/customer/items")
public class Cus_ItemController {
    @Autowired
    private ItemRepository repository;

    @GetMapping("")
    List<Item> getAllItems(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<ResObject> findById(@PathVariable int id){
        Optional<Item> foundItem = repository.findById(id);
        if (foundItem.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResObject("ok","succeeded",foundItem)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResObject("false","Cannot find "+id,"")
            );
        }
    }

}
