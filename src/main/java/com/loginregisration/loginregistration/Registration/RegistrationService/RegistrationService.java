package com.loginregisration.loginregistration.Registration.RegistrationService;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.loginregisration.loginregistration.Registration.RegistrationRequset;
import com.loginregisration.loginregistration.Tokens.InvitationToken.InvitationToken;
import com.loginregisration.loginregistration.Tokens.InvitationToken.TokenService.InvitationTokenService;
import com.loginregisration.loginregistration.User.UserService.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegistrationService {
    
    private final UserService userService;
    private final InvitationTokenService invitationTokenService;

    @Transactional
    public String registration(RegistrationRequset registration, String token){
        
        InvitationToken invitationToken = invitationTokenService.getToken(token).orElse(null);

        if(invitationToken == null) return "Token not found";            


        if(invitationToken.getExpiredAt().isBefore(LocalDateTime.now())) {
            invitationTokenService.DeleteByToken(token);
            userService.DeleteUser(invitationToken.getUser().getEmail());
            return "Token expired and removed please ask for another invitation";
        }


        userService.RegisterUser(invitationToken.getUser().getEmail(), registration.username, registration.password);

        invitationTokenService.DeleteByToken(token);
        return "Confirmed";
    }

}
