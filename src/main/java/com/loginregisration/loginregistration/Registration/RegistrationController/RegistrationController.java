package com.loginregisration.loginregistration.Registration.RegistrationController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loginregisration.loginregistration.Registration.RegistrationRequset;
import com.loginregisration.loginregistration.Registration.RegistrationService.RegistrationService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {
    
    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<String>  registration(@RequestBody RegistrationRequset registration, @RequestParam("token") String token){
        
        final String ServiceResponse = registrationService.registration(registration, token);
        
        if(ServiceResponse.equals("Token not found")) return ResponseEntity.status(404).body(ServiceResponse); 

        if(ServiceResponse.equals("Token expired and removed please ask for another invitation")) return ResponseEntity.status(409).body(ServiceResponse);

        return ResponseEntity.ok(ServiceResponse); 
    }
}
