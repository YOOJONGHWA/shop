package com.apple.shop.item;

import com.apple.shop.comment.Comment;
import com.apple.shop.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ItemRepository itemRepository;
    private final CommentRepository commentRepository;

    @GetMapping("/list")
    String list(Model model) {
        List<Item> result = itemService.findAll();
        System.out.println(result);
        model.addAttribute("items", result);
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
        List<Comment> result2 = commentRepository.findByParentId(id);
        model.addAttribute("comment", result2);

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
        itemService.deleteById(id);
        return ResponseEntity.status(200).body("삭제완료");
    }

    @GetMapping("/list/page/{page}")
    String getListPage(Model model, @PathVariable Integer page) {
        int pageSize = 5;
        Page<Item> result = itemService.findPageBy(page, pageSize);
        model.addAttribute("items", result.getContent());
        model.addAttribute("totalPages", result.getTotalPages());
        model.addAttribute("currentPage", page);

        return "list.html";
    }

    @PostMapping("/search")
    String postSearch(String searchText, Model model) {
        var result = itemService.rawQuery(searchText);
        model.addAttribute("searchText", result);
        return "list.html";
    }

}
