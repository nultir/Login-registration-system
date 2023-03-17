package com.loginregisration.loginregistration.Invitation.InvitationController;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loginregisration.loginregistration.Invitation.InvitationRequest;
import com.loginregisration.loginregistration.Invitation.InvitationService.InvitationService;
import com.loginregisration.loginregistration.Tokens.JWT.JwtUtils;
import com.loginregisration.loginregistration.User.UserEntity.UserEntity;
import com.loginregisration.loginregistration.User.UserEntity.UserRole;
import com.loginregisration.loginregistration.User.UserService.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "api/v1/invitation")
@AllArgsConstructor
public class InvitationController {

    private final InvitationService invitationService;
    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final SimpleGrantedAuthority AdminAuth = new SimpleGrantedAuthority(UserRole.Admin.name());
    @PostMapping
    public ResponseEntity<String> invitation(@RequestBody InvitationRequest invitation, @RequestHeader("Authorization") String BearerToken){
        final String UserEmail = jwtUtils.extractEmail(BearerToken.split(" ")[1]);
        UserEntity User = userService.loadUserByUsername(UserEmail);
        if(User.getAuthorities().contains(AdminAuth)){
            //TODO Valid email response
            return ResponseEntity.ok(invitationService.invitation(invitation));
        }
        return ResponseEntity.status(403).body("Access not granted");
        
    }
}
