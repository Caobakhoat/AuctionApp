package com.example.demo_web.controller;

import com.example.demo_web.config.FileUploadUtil;
import com.example.demo_web.config.MessageConfig;
import com.example.demo_web.model.Item;
import com.example.demo_web.response.GetAllItemResponse;
import com.example.demo_web.response.AddItemResponse;
import com.example.demo_web.service.ItemServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {
    @Autowired
    ItemServiceImpl itemServiceIpml;
    @Autowired
    private final MessageConfig messageConfig;
    private static final String EXTERNAL_FILE_PATH = "/Users/bakhoat/Documents/demo_web/src/main/resources/static/item-photos/";

    @PostMapping(value = "/addItem")
    public ResponseEntity addItem(@RequestParam("imageItem") MultipartFile multipartFile, @RequestParam String description, @RequestParam String name) throws IOException {
        AddItemResponse res = new AddItemResponse();
        Item item = new Item();
        item.setDescription(description);
        item.setName(name);
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        item.setNameImage(fileName);
        Item saveItem = itemServiceIpml.saveItem(item);
        String uploadDir = "src/main/resources/static/item-photos/" + saveItem.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        res.setCode(messageConfig.CODE_SUCCESS);
        res.setMessage(messageConfig.MESSAGE_ADDITEM);
        res.setResult(saveItem);

        return ResponseEntity.ok().body(res) ;
    }
    @GetMapping(value = "/getAllItem")
    public ResponseEntity getAllItemResponse (){
        GetAllItemResponse res = new GetAllItemResponse();
        res =itemServiceIpml.getAllItem();
        return ResponseEntity.ok().body(res) ;
    }


        @RequestMapping("/imageItem/{id}/{fileName:.+}")
        public void downloadPDFResource(HttpServletRequest request, HttpServletResponse response,
                                        @PathVariable("fileName") String fileName, @PathVariable("id") String id) throws IOException {
            File file = new File(EXTERNAL_FILE_PATH+id+"/" + fileName);
            if (file.exists()) {
                String mimeType = URLConnection.guessContentTypeFromName(file.getName());
                if (mimeType == null) {
                    mimeType = "application/octet-stream";
                }
                response.setContentType(mimeType);
                response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
                response.setContentLength((int) file.length());
                InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
                FileCopyUtils.copy(inputStream, response.getOutputStream());

            }
        }
    }

