package com.synclab.challenginatorUserService.signup;

import com.synclab.challenginatorUserService.signin.SignInRequest;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;



/*
Richiesta di registrazione, definisce i campi in input
 */

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SignUpRequest {

    private final String name;
    private final String surname;
    private final String password;
    private final String email;

    // Verifico se la richiesta ha tutti i campi necessari per fare il signUp
    public boolean requestCheck(){
        if(this.getName() != null && this.getSurname() !=null && this.getPassword() != null && this.getEmail() != null)
            return true;
            else return false;
    }






}
