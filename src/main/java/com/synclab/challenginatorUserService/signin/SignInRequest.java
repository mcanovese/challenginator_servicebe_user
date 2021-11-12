package com.synclab.challenginatorUserService.signin;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


/*
Definizione della SignIn Request che contiene appunto solamente
email e password
 */

@Getter
@AllArgsConstructor

@EqualsAndHashCode
@ToString
public class SignInRequest {

    //definizione della richiesta di login che riceve l'endpoint sign-in

    private final String email;
    private final String password;

}
