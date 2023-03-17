package com.loginregisration.loginregistration.Tokens.InvitationToken.TokenService;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.loginregisration.loginregistration.Tokens.InvitationToken.InvitationToken;
import com.loginregisration.loginregistration.Tokens.InvitationToken.TokenRepository.InvitationTokenRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InvitationTokenService {
    
    private final InvitationTokenRepository invitationTokenRepository;

    public void saveinvitationToken(InvitationToken token){
        invitationTokenRepository.save(token);
    }

    public Optional<InvitationToken> getToken(String token){
        return invitationTokenRepository.findByToken(token);
    }

    public int DeleteByToken(String token){
        return invitationTokenRepository.DeleteByToken(token);
    }
}
