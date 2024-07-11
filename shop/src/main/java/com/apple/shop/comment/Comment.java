package com.apple.shop.comment;

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
public class Comment extends BaseEntity {

    private String displayName;
    @Column(length = 100)
    private String content;
    private Long parentId;

}
