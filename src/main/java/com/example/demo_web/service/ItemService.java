package com.example.demo_web.service;

import com.example.demo_web.model.Item;
import com.example.demo_web.response.BaseResponse;
import com.example.demo_web.response.GetAllItemResponse;

import java.util.ArrayList;

public interface ItemService {
    Item saveItem(Item item);
    GetAllItemResponse getAllItem();
}
