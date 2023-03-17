package com.loginregisration.loginregistration.Registration;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class RegistrationRequset {

    public final String username;
    public final String password;
    
    public RegistrationRequset(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    
}
