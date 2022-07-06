package com.example.demo_web.service;

import com.example.demo_web.model.Item;
import com.example.demo_web.repository.ItemRepository;
import com.example.demo_web.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

}
