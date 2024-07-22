package com.apple.shop.sales;

import com.apple.shop.member.CustomUser;
import com.apple.shop.member.Member;
import com.apple.shop.member.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class SalesController {

    private final SalesService salesService;
    private final SalesRepository salesRepository;
    private final MemberRepository memberRepository;

    @PostMapping("/sales")
    String postOrder(@RequestBody Map<String, String> data, Authentication auth) {
        String title = data.get("title");
        Integer price = Integer.valueOf(data.get("price"));
        Integer count = Integer.valueOf(data.get("count"));
        salesService.save(title, price, count, auth);
        System.out.println(title + " " + price + " " + count);
        return "redirect:/list";
    }

    @GetMapping("/order/all")
    String getOrders(Model model) {
        List<SalesDto> salesDtoList = salesService.customFindAll();
        model.addAttribute("salesList", salesDtoList);

        return "orders.html";
    }

}
