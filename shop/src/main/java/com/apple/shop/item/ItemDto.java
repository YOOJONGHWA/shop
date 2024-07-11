package com.apple.shop.item;

import lombok.Getter;

@Getter
public class ItemDto {

    private String title;
    private Integer price;

    public ItemDto(Item item) {
        this.title = item.getTitle();
        this.price = item.getPrice();
    }
}
