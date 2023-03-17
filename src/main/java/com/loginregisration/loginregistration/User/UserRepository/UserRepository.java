package com.loginregisration.loginregistration.User.UserRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.loginregisration.loginregistration.User.UserEntity.UserEntity;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<UserEntity, Integer>{
    Optional<String> findByUsername(String username);
    Optional<UserEntity> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE UserEntity " +
            "SET username = ?1, password = ?2, enabled = true " +
            "WHERE email = ?3"
            )
    public void RegisterUser(String username, String password, String email);
    
    @Transactional
    @Modifying
    @Query("Delete from UserEntity " +
    "WHERE email = ?1")
    int DeleteByEmail(String email);
}
