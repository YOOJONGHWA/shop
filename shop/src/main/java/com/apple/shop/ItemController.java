package com.apple.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/list")
    String list(Model model) {
        List<Item> result = itemService.findAll();
        model.addAttribute("items",result);
        return "list.html";
    }

    @GetMapping("/write")
    String write() {
        return "write.html";
    }


    @PostMapping ("/add")
    String addPost(String title, Integer price)  {
        itemService.saveItem(title,price);
        return "redirect:/list";
    }

    @GetMapping("/detail/{id}")
    String detail(@PathVariable Long id, Model model) throws Exception {

        Optional<Item> result = itemService.findById(id);
        if (result.isPresent()){
            model.addAttribute("data", result.get());
            return "detail.html";
        } else {
            return "redirect:/list";
        }
//        throw new Exception();
    }
}
