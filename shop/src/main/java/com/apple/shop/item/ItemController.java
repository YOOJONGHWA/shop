package com.apple.shop.item;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ItemRepository itemRepository;

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
    @GetMapping("/edit/{id}")
    String editForm(@PathVariable Long id, Model model) {
        Optional<Item> result = itemService.findById(id);
        if (result.isPresent()) {
            model.addAttribute("data", result.get());
            return "edit.html";
        }
        else {
            return "redirect:/list";
        }
    }
    @PostMapping("/edit")
    String editItem(Long id, String title, Integer price) {
        itemService.editItem(id,title,price);
       return "redirect:/list";
    }

    @GetMapping("/test")
    String handleTestRequest() {
        System.out.println("요청 '/test' 수신");
        return "redirect:/list";
    }

    @DeleteMapping("/item")
    ResponseEntity<String> deleteItem(@RequestParam Long id) {
        itemRepository.deleteById(id);
        return ResponseEntity.status(200).body("삭제완료");
    }

}
