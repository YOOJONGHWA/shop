package com.apple.shop.sales;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SalesDto  {

    private String title;
    private Integer price;
    private Integer count;
    private String username;
    private Long memberId;
    private LocalDateTime created;

}
