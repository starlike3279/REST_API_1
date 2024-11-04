package com.example.demo.domain.member.controller;

import com.example.demo.domain.member.dto.MemberDTO;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.request.MemberRequest;
import com.example.demo.domain.member.service.MemberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/members", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "ApiV1MemberController", description = "회원 인증인가 API")
public class ApiV1MemberController {
    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<MemberDTO> join (@Valid @RequestBody MemberRequest memberRequest) {
        Member member = this.memberService.join(memberRequest.getUsername(), memberRequest.getPassword());

        return ResponseEntity.ok(new MemberDTO(member));
    }

    @PostMapping("/login")
    public ResponseEntity login (@Valid @RequestBody MemberRequest memberRequest, HttpSession httpSession) {
        Member member = this.memberService.getMember(memberRequest.getUsername());

        if (member == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("존재하지 않는 회원입니다.");
        }

        httpSession.setAttribute("USER_ID", member.getUsername());

        return ResponseEntity.ok("로그인 성공");
    }

    @GetMapping("/logout")
    public ResponseEntity logout(HttpSession httpSession) {

        httpSession.invalidate();

        return ResponseEntity.ok("로그아웃 성공");
    }
}