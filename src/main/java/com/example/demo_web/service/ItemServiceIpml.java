package com.example.demo_web.service;

import com.example.demo_web.config.MessageConfig;
import com.example.demo_web.model.Item;
import com.example.demo_web.repository.ItemRepository;
import com.example.demo_web.response.GetAllItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor

public class ItemServiceIpml implements ItemService{
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

}
