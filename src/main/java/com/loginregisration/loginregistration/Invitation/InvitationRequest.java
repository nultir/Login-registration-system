package com.loginregisration.loginregistration.Invitation;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class InvitationRequest {
    private final String  email;

    @JsonCreator
    public InvitationRequest(@JsonProperty("email") String email){
        this.email = email;
    }
    
}
