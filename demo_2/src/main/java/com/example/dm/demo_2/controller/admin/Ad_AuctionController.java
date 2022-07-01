package com.example.dm.demo_2.controller.admin;

import com.example.dm.demo_2.model.Auction;
import com.example.dm.demo_2.model.Item;
import com.example.dm.demo_2.model.ResObject;
import com.example.dm.demo_2.model.User;
import com.example.dm.demo_2.repository.AuctionRepository;
import com.example.dm.demo_2.repository.ItemRepository;
import com.example.dm.demo_2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/admin/auctions")
public class Ad_AuctionController {
    @Autowired
    private AuctionRepository repository;
    private UserRepository userRepository;
    private ItemRepository itemRepository;

    @GetMapping("")
    List<Auction> getAllAuctions(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<ResObject> findById(@PathVariable int id){
        Optional<Auction> foundAuction = repository.findById(id);
        if (foundAuction.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResObject("ok","succeeded",foundAuction)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResObject("false","Cannot find "+id,"")
            );
        }
    }

    @PostMapping("/add")
    ResponseEntity<ResObject> addAuction(@RequestBody Auction newAuction){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResObject("ok","succeeded",repository.save(newAuction))
        );
    }

    @PutMapping("/{id}")
    ResponseEntity<ResObject> updateAuction(@RequestBody Auction newAuction,@PathVariable int id){
        //Optional<Auction> foundAuction = repository.findById(id);
        Auction updatedAuction = repository.findById(id).map(Auction -> {
            Auction.setStart_at(newAuction.getStart_at());
            Auction.setEnd_at(newAuction.getEnd_at());
            //Auction.setCreate_at(foundAuction.getClass().);
            Auction.setInitPrice(newAuction.getInitPrice());
            Auction.setStatus(newAuction.getStatus());
            Auction.setModify_at(LocalDateTime.now().toString());
            Optional<User> optionalHost = userRepository.findById(id);
            User host = optionalHost.get();
            Auction.setHost(host);
            Optional<Item> optionalItem = itemRepository.findById(id);
            Item item = optionalItem.get();
            Auction.setItem(item);
            return repository.save(Auction);
        }).orElseGet(()->{
            newAuction.setId(id);
            return repository.save(newAuction);
        });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResObject("ok","Updated",updatedAuction)
        );
    }

    @DeleteMapping("id")
    ResponseEntity<ResObject> deleteAuction(@PathVariable int id){
        //Optional<Auction> foundAuction = repository.findById(id);
        boolean ex = repository.existsById(id);
        if (ex){
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResObject("ok","Deleted","")
            );
        }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResObject("false", "Auction not existed", "")
            );

    }
}
