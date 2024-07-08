package com.apple.shop.member;

import com.apple.shop.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
public class Member extends BaseEntity {
    @Column(unique = true)
    private String username;
    private String password;
    private String displayName ;

}
