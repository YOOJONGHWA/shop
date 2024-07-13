package com.apple.shop.sales;

import com.apple.shop.member.CustomUser;
import com.apple.shop.member.Member;
import com.apple.shop.member.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class SalesController {

    private final SalesService salesService;
    private final SalesRepository salesRepository;
    private final MemberRepository memberRepository;

    @PostMapping("/sales")
    String postOrder(String title,
                     Integer price,
                     Integer count,
                     Authentication auth) {
        salesService.save(title,price,count,auth);
        return "redirect:/list";

    }

    @GetMapping("/order/all")
    String getOrders(Model model) {
        List<SalesDto> salesDtoList = salesService.customFindAll();
        model.addAttribute("salesList", salesDtoList);
        var result = memberRepository.findById(1L);
        System.out.println(result.get().getSales());
        return "orders.html";
    }

}
