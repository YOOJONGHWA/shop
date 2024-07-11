package com.apple.shop.item;

import com.apple.shop.member.Member;
import com.apple.shop.member.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void saveItem(String title, Integer price) {
        Item item = new Item();
        item.setTitle(title);
        item.setPrice(price);
        itemRepository.save(item);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Optional<Item> findById(Long id) {
        return itemRepository.findById(id);
    }

    public void editItem(Long id, String title, Integer price) {
        // 아이템을 데이터베이스에서 가져옴
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();

            // 제목 유효성 검사
            if (title.length() > 100) {
                throw new IllegalArgumentException("제목은 100자 이하여야 합니다.");
            } else {
                item.setTitle(title);
            }

            // 가격 유효성 검사
            if (price < 0) {
                throw new IllegalArgumentException("가격은 음수가 될 수 없습니다.");
            } else {
                item.setPrice(price);
            }

            // 수정된 아이템을 저장
            itemRepository.save(item);
        } else {
            throw new IllegalArgumentException("해당 ID의 아이템을 찾을 수 없습니다: " + id);
        }
    }

    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }


    public Page<Item> findPageBy(int page, int size) {
        return itemRepository.findAll(PageRequest.of(page, size));
    }

}
