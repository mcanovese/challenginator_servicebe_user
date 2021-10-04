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

    private final String jwt;

}
