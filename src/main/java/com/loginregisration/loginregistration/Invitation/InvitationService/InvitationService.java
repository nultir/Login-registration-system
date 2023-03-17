package com.loginregisration.loginregistration.Invitation.InvitationService;


import org.springframework.stereotype.Service;

import com.loginregisration.loginregistration.Email.EmailValidator.EmailValidator;
import com.loginregisration.loginregistration.Invitation.InvitationRequest;
import com.loginregisration.loginregistration.User.UserEntity.UserEntity;
import com.loginregisration.loginregistration.User.UserEntity.UserRole;
import com.loginregisration.loginregistration.User.UserService.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InvitationService {

    private final UserService userService;
    private final EmailValidator emailValidator;

    public String invitation(InvitationRequest invitation) {
        
        boolean isValidemail = emailValidator.test(invitation.getEmail());

        if(!isValidemail){
            throw new IllegalStateException("Email is not valid");
        }
        return userService.InviteUser(
            new UserEntity(
                invitation.getEmail(),
                UserRole.Normal_User
            )
        );
    }
    
}
