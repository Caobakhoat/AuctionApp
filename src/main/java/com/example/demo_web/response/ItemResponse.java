package com.example.demo_web.response;

import com.example.demo_web.model.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse  {
    private Item item;
    private byte[] imageItem;
}
