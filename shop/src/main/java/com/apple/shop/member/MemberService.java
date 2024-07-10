package com.apple.shop.member;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void signMember(String username, String password, String displayName) throws Exception {
        if (username.length() < 3 || password.length() < 3) {
            throw new IllegalArgumentException("사용자명과 비밀번호는 최소 3자 이상이어야 합니다.");
        }

        if (memberRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 사용자명입니다.");
        }

        Member member = Member.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .displayName(displayName)
                .build();

        memberRepository.save(member);
    }


    public MemberDto findById(long id) {
        var optionalMember = memberRepository.findById(id);
        if (optionalMember.isPresent()) {
            var member = optionalMember.get();
            var memberDto = new MemberDto();
            memberDto.setUsername(member.getUsername());
            memberDto.setDisplayName(member.getDisplayName());
            return memberDto;
        }
        else {
            return null;
        }
    }

    public MemberDto findById2(long id) {
        var optionalMember = memberRepository.findById(id);
        if (optionalMember.isPresent()) {
            var member = optionalMember.get();
            var memberDto = new MemberDto();
            memberDto.setUsername(member.getUsername());
            memberDto.setDisplayName(member.getDisplayName());
            memberDto.setId(member.getId());
            return memberDto;
        }
        else {
            return null;
        }
    }
}
