package com.seunghoona.kmong.member.ui;

import com.seunghoona.kmong.member.application.MemberService;
import com.seunghoona.kmong.member.dto.JoinRequest;
import com.seunghoona.kmong.member.dto.JoinResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JoinResponse> join(@RequestBody JoinRequest joinRequest) {
        JoinResponse savedMember = memberService.join(joinRequest);
        return ResponseEntity.created(URI.create("/members/" + savedMember.getId())).body(savedMember);
    }
}
