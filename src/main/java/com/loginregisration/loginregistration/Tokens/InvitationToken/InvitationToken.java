package com.loginregisration.loginregistration.Tokens.InvitationToken;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.loginregisration.loginregistration.User.UserEntity.UserEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class InvitationToken {

    @Id
    @SequenceGenerator(
        name = "token_sequence",
        sequenceName = "token_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy =  GenerationType.SEQUENCE,
        generator = "token_sequence"
    )
    private int id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiredAt;


    @ManyToOne
    @JoinColumn(
        nullable = false,
        name = "login_id"
    )
    private UserEntity user;

    public InvitationToken(String token, LocalDateTime createdAt, LocalDateTime expiredAt,
             UserEntity user) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.user = user;
    }
}
