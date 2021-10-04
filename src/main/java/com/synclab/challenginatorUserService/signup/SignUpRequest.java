package com.synclab.challenginatorUserService.signup;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SignUpRequest {

    private final String name;
    private final String surname;
    private final String password;
    private final String email;






}
