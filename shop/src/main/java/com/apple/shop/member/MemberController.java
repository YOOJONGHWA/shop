package com.apple.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @GetMapping("/test2")
    String deleteItem() {
        var result = new BCryptPasswordEncoder().encode("문자~");
        System.out.println(result);
        return "redirect:/list";
    }

    @GetMapping("/signup")
    String signup() {
        return "signup.html";
    }

    @PostMapping("/signup")
    String signMember(String username,
                      String password,
                      String displayName,
                      Model model) throws Exception {
        memberService.signMember(username,password,displayName);
        model.addAttribute("username", username);
        return "redirect:/list";
    }
    @GetMapping("/login")
    String login() {

        return "login.html";
    }

    @GetMapping("/my-page")
    String myPage(Authentication auth)  {
        MyUserDetailsService.CustomUser result = (MyUserDetailsService.CustomUser) auth.getPrincipal();
        System.out.println(result.displayName);
        return "myPage.html";
    }

    @GetMapping("/logout")
    String logout() {
        return "index.html";
    }

    @GetMapping("/user/1")
    public ResponseEntity<MemberDto> getUser() {
        MemberDto memberDto = memberService.findById(1L);
        if (memberDto != null) {
            return ResponseEntity.ok(memberDto);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("v2/user/1")
    public ResponseEntity<MemberDto> getUser2() {
        MemberDto memberDto = memberService.findById2(1L);
        if (memberDto != null) {
            return ResponseEntity.ok(memberDto);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

}
