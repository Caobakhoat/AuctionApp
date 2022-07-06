package com.example.demo_web.controller;

import com.example.demo_web.config.FileUploadUtil;
import com.example.demo_web.model.Item;
import com.example.demo_web.response.GetAllItemResponse;
import com.example.demo_web.response.AddItemResponse;
import com.example.demo_web.service.ItemServiceIpml;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {
    @Autowired
    ItemServiceIpml itemServiceIpml;

    @PostMapping(value = "/addItem")
    public AddItemResponse addItem(@RequestParam("imageItem") MultipartFile multipartFile, @RequestParam String description, @RequestParam String name) throws IOException {
        AddItemResponse res = new AddItemResponse();
        Item item = new Item();
        item.setDescription(description);
        item.setName(name);
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        item.setNameImage(fileName);

        Item saveItem = itemServiceIpml.saveItem(item);

        String uploadDir = "src/main/resources/static/item-photos/" + saveItem.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        res.setCode(1);
        res.setMessage("addItem success");
        res.setResult(saveItem);
        return res;
    }
    @GetMapping(value = "/getAllItem")
    public GetAllItemResponse getAllItemResponse (){
        GetAllItemResponse res = new GetAllItemResponse();
        res =itemServiceIpml.getAllItem();
        return res;
    }
}