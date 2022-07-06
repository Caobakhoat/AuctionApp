package com.example.demo_web.service;

import com.example.demo_web.model.Item;
import com.example.demo_web.repository.ItemRepository;
import com.example.demo_web.response.BaseResponse;
import com.example.demo_web.response.GetAllItemResponse;
import com.example.demo_web.response.ItemResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        ArrayList<ItemResponse> listItemRes = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            Path filePath = Path.of("src/main/resources/static/item-photos/"+list.get(i).getId()+"/"+list.get(i).getNameImage());
            System.out.println(filePath);
            try
            {
                byte[] bytes = Files.readAllBytes(Paths.get(String.valueOf(filePath)));
                ItemResponse itemResponse = new ItemResponse();
                itemResponse.setItem(list.get(i));
                itemResponse.setImageItem(bytes);
                listItemRes.add(itemResponse);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        res.setCode(1);
        res.setMessage("get all item success");
        res.setResult(listItemRes);
        return res;
    }

}
