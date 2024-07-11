package com.apple.shop.comment;

import com.apple.shop.item.Item;
import com.apple.shop.item.ItemRepository;
import com.apple.shop.item.ItemService;
import com.apple.shop.member.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;
    private final CommentService commentService;


    @PostMapping ("/comment")
    String addComment(String content,
                      Long parent,
                      Authentication auth)  {

        commentService.addComment(content,parent,auth);
        return "redirect:/list";
    }

}
