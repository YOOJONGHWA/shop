package com.apple.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final itemRepository itempRepository;

    @GetMapping("/list")
    String list(Model model) {
        List<item> result = itempRepository.findAll();
        model.addAttribute("items",result);
        return "list.html";
    }

    @GetMapping("/write")
    String write() {
        return "write.html";
    }


    @PostMapping ("/add")
    String addPost(@ModelAttribute item item)  {
        itempRepository.save(item);


        return "redirect:/list";
    }

    @GetMapping("/detail/{id}")
    String detail(@PathVariable Long id, Model model) throws Exception {

//        Optional<item> result = itempRepository.findById(id);
//        if (result.isPresent()){
//            model.addAttribute("data", result.get());
//            return "detail.html";
//        } else {
//            return "redirect:/list";
//        }
        throw new Exception();
    }
}
