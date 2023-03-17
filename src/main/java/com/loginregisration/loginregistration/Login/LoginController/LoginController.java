package com.loginregisration.loginregistration.Login.LoginController;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loginregisration.loginregistration.Login.Login;
import com.loginregisration.loginregistration.Tokens.JWT.JwtUtils;
import com.loginregisration.loginregistration.User.UserEntity.UserEntity;
import com.loginregisration.loginregistration.User.UserService.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "api/v1/login")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtils jwtUtils;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate( @RequestBody Login requset){
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(requset.getEmail(), requset.getPassword()));
        final UserEntity user = userService.loadUserByUsername(requset.getEmail());
        if (user != null) return ResponseEntity.ok(jwtUtils.generateToken(user));
        return ResponseEntity.status(400).body("Some error has occured");
    }

}
