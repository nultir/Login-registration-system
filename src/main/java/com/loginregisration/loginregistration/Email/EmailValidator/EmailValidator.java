package com.loginregisration.loginregistration.Email.EmailValidator;

import java.util.function.Predicate;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;


@Service
public class EmailValidator implements Predicate<String> {

    private final String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    
    @Override
    public boolean test(String email) {
        return Pattern.compile(regex).matcher(email).matches();
    }
    
}
