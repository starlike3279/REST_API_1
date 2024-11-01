package com.example.demo.domain.member.dto;


import com.example.demo.domain.member.entity.Member;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberDTO {

    private final Long id;
    private final String username;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;

    public MemberDTO(Member article) {

        this.id = article.getId();
        this.username = article.getUsername();
        this.createdDate = article.getCreatedDate();
        this.modifiedDate = article.getModifiedDate();

    }

}