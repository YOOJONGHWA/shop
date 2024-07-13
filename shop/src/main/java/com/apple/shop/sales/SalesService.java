package com.apple.shop.sales;

import com.apple.shop.member.CustomUser;
import com.apple.shop.member.Member;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SalesService {

    private final SalesRepository salesRepository;

    public void save(String title, Integer price, Integer count, Authentication auth) {
        // 수량 입력 여부 검증
        if (count == null) {
            throw new IllegalArgumentException("수량을 입력하세요.");
        }

        // 가격과 수량 제한 검증
        if (price * count > 100000000) {
            throw new IllegalArgumentException("100,000,000원 이하만 주문 가능합니다.");
        }

        // 사용자 인증 검증
        if (auth == null || auth.getPrincipal() == null) {
            throw new IllegalArgumentException("로그인 된 사용자만 주문 가능합니다.");
        }

        // 사용자 정보 설정
        CustomUser user = (CustomUser) auth.getPrincipal();
        Member member = new Member();
        member.setId(user.id);

        // 주문 객체 설정 및 저장
        Sales sales = new Sales();
        sales.setTitle(title);
        sales.setPrice(price);
        sales.setCount(count);
        sales.setMember(member);

        salesRepository.save(sales);
    }

    public List<SalesDto> customFindAll() {
        List<Sales> salesList = salesRepository.customFindAll();

        return salesList.stream()
                .map(sales -> {
                    SalesDto dto = new SalesDto();
                    dto.setTitle(sales.getTitle());
                    dto.setCount(sales.getCount());
                    dto.setPrice(sales.getPrice() * sales.getCount());
                    dto.setUsername(sales.getMember().getUsername());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
