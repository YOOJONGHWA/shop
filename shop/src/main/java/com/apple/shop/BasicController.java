package com.apple.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.ZonedDateTime;

@Controller
public class BasicController {
    @GetMapping("/")
    String main() {
        return "index.html";
    }

    @GetMapping("/date")
    @ResponseBody
    String date() {
        System.out.println("안녕하십니까");




        return ZonedDateTime.now().toString();
    }

}


