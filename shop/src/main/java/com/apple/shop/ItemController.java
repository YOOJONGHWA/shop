package com.apple.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final itemRepository itempRepository;

    @GetMapping("/list")
    String list(Model model) {
        List<item> result = itempRepository.findAll();
        model.addAttribute("items",result);
        System.out.println(result);

        return "list.html";
    }

}
