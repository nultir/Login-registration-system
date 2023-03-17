package com.loginregisration.loginregistration.Tokens.InvitationToken.TokenRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.loginregisration.loginregistration.Tokens.InvitationToken.InvitationToken;

@Repository
public interface InvitationTokenRepository extends JpaRepository<InvitationToken, Integer> {

    Optional<InvitationToken> findByToken(String Token);

    @Transactional
    @Modifying
    @Query("Delete from InvitationToken " +
    "WHERE token = ?1")
    int DeleteByToken(String token);
    
}