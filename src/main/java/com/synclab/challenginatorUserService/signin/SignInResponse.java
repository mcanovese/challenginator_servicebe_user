package com.synclab.challenginatorUserService.signin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


/*
definizione risposta endpoint signin - solo il token jwt
 */

@Getter
@Setter
@AllArgsConstructor
public class SignInResponse {

    // riposta al client che ha effettuato un sign-in, gli ritorna il jwt e l'userId dell'utente

    private final String jwt;
    private final String userId;

}
