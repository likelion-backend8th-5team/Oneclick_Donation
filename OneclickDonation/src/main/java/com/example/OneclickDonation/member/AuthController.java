package com.example.OneclickDonation.member;

import com.example.OneclickDonation.jwt.JwtRequestDto;
import com.example.OneclickDonation.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
// 필요한 다른 import문 추가

@RestController
@RequestMapping("/donation")
public class AuthController {

    @Autowired
    private MemberService service;

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody JwtRequestDto dto) {
        String token = String.valueOf(service.signin(dto));
        if (token != null) {
           return ResponseEntity.ok().body(service.signin(dto));
            //ResponseEntity.ok().body(Map.of("token", token));
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("message", "로그인 실패"));
        }
    }
}