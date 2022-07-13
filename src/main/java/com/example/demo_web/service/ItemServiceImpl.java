package com.example.demo_web.service;

import com.example.demo_web.config.MessageConfig;
import com.example.demo_web.model.Item;
import com.example.demo_web.repository.ItemRepository;
import com.example.demo_web.response.DeleteItemResponse;
import com.example.demo_web.response.GetAllItemResponse;
import com.example.demo_web.response.UpdateItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor

public class ItemServiceImpl implements ItemService{
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    MessageConfig messageConfig;
    @Override
    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public GetAllItemResponse getAllItem() {
        ArrayList<Item> listItem =(ArrayList<Item>) itemRepository.findAll();
        for (Item item : listItem) {
            if(item.getIsDelete()==1){
                listItem.remove(item);
            }
        }
        GetAllItemResponse res = new GetAllItemResponse();
        res.setCode(messageConfig.CODE_SUCCESS);
        res.setMessage(messageConfig.MESSAGE_GETALLITEM);
        res.setResult(listItem);
        return res;
    }

    @Override
    public UpdateItemResponse updateItem(Item item) {
        UpdateItemResponse res = new UpdateItemResponse();
        res.setCode(messageConfig.CODE_SUCCESS);
        res.setMessage(messageConfig.MESSAGE_UPDATEITEM);
        res.setResult(itemRepository.save(item));
        return res;
    }

    @Override
    public DeleteItemResponse deleteItem(Item item) {
        DeleteItemResponse res = new DeleteItemResponse();
        item.setIsDelete(1);
        updateItem(item);
        res.setCode(messageConfig.CODE_SUCCESS);
        res.setMessage(messageConfig.MESSAGE_DELETEITEM);
        res.setResult(true);
        return res;
    }



}
