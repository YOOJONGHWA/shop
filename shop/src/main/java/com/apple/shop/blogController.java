package com.apple.shop;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class blogController {

    private final blogRepository blogRepository;

    @GetMapping("/blog")
    String list(Model model) {
        List<blog> result = blogRepository.findAll();
        model.addAttribute("blogs", result);
        return "blog.html";
    }
}