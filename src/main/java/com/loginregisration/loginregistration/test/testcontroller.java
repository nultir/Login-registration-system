package com.loginregisration.loginregistration.test;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loginregisration.loginregistration.Tokens.JWT.JwtUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "api/v1/test")
@RequiredArgsConstructor
public class testcontroller {
    
    private final JwtUtils jwtUtils;

    @GetMapping
    public ResponseEntity<String> test(@RequestHeader(HttpHeaders.AUTHORIZATION) String b){
        System.out.println(b);
        System.out.println(jwtUtils.extractEmail(b.split(" ")[1]));
        return ResponseEntity.ok("test");
    }
}
