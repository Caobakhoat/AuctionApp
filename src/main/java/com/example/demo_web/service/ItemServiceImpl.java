package com.example.demo_web.service;

import com.example.demo_web.config.MessageConfig;
import com.example.demo_web.model.Item;
import com.example.demo_web.model.Item;
import com.example.demo_web.model.User;
import com.example.demo_web.repository.ItemRepository;
import com.example.demo_web.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ItemServiceImpl implements ItemService{
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    MessageConfig messageConfig;
    @Override
    public Item saveItem(Item item) {
        Item i= itemRepository.save(item);
        return i;
    }

    @Override
    public GetAllItemResponse getAllItem() {
        ArrayList<Item> list =(ArrayList<Item>) itemRepository.findAll();
        GetAllItemResponse res = new GetAllItemResponse();
        res.setCode(messageConfig.CODE_SUCCESS);
        res.setMessage(messageConfig.MESSAGE_GETALLITEM);
        res.setResult(list);
        return res;
    }

    @Override
    public UpdateItemResponse updateItem(Item newItem, int id) {
        Item updatedItem = itemRepository.findById(id).map(Item -> {
            Item.setName(newItem.getName());
            Item.setNameImage(newItem.getNameImage());
            //Item.setCreate_at(foundItem.getClass().);
            Item.setDescription(newItem.getDescription());
            Item.setCreatAt(newItem.getCreatAt());
            Item.setModifyAt(LocalDateTime.now());

            return itemRepository.save(Item);
        }).orElseGet(()->{
            newItem.setId(id);
            return itemRepository.save(newItem);
        });
        UpdateItemResponse res = new UpdateItemResponse();
        res.setCode(1);
        res.setMessage("get Item "+id+" succeeded");
        res.setResult(updatedItem);
        return res;
    }

    @Override
    public DeleteItemResponse deleteItem(int id) {
        DeleteItemResponse res = new DeleteItemResponse();
        itemRepository.deleteById(id);
        res.setCode(1);
        res.setMessage("Delete Item "+id+" succeeded");
        res.setResult(null);
        return res;
    }

}
