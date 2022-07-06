package com.example.demo_web.service;

import com.example.demo_web.model.Item;
import com.example.demo_web.repository.ItemRepository;
import com.example.demo_web.response.BaseResponse;
import com.example.demo_web.response.GetAllItemResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor

public class ItemServiceIpml implements ItemService{
    @Autowired
    ItemRepository itemRepository;
    @Override
    public Item saveItem(Item item) {
        Item i= itemRepository.save(item);
        return i;
    }

    @Override
    public GetAllItemResponse getAllItem() {
        ArrayList<Item> list =(ArrayList<Item>) itemRepository.findAll();
        GetAllItemResponse res = new GetAllItemResponse();
        res.setResult(list);
        res.setCode(1);
        res.setMessage("get all item success");
        return res;
    }

}
