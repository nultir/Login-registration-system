package com.loginregisration.loginregistration.User.UserService;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.loginregisration.loginregistration.Tokens.InvitationToken.InvitationToken;
import com.loginregisration.loginregistration.Tokens.InvitationToken.TokenService.InvitationTokenService;
import com.loginregisration.loginregistration.User.UserEntity.UserEntity;
import com.loginregisration.loginregistration.User.UserRepository.UserRepository;

import lombok.AllArgsConstructor;



@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final static String USER_NOTFOUND = "User with email %s not found";
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private final InvitationTokenService invitationTokenService;

    @Override
    public UserEntity loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOTFOUND, email)));
    }

    public String InviteUser(UserEntity user){
        boolean UserExist = userRepository.findByEmail(user.getEmail()).isPresent();
        
        if(UserExist) throw new IllegalStateException("email already taken");
        
        userRepository.save(user);
        
        String token = UUID.randomUUID().toString();

        InvitationToken invitationtoken = new InvitationToken(
            token,
            LocalDateTime.now(),
            LocalDateTime.now().plusDays(1),
            user
        );

        invitationTokenService.saveinvitationToken(invitationtoken);

        return token;
    }

    public void RegisterUser(String email, String username, String password){
        boolean Usernameexist = userRepository.findByUsername(username).isPresent();

        if(Usernameexist) throw new IllegalStateException("username already taken");

        String encodedpassword =  bCryptPasswordEncoder.encode(password);

        userRepository.RegisterUser(username, encodedpassword, email);
    }

    public void DeleteUser(String email){
        userRepository.DeleteByEmail(email);
    }
}
