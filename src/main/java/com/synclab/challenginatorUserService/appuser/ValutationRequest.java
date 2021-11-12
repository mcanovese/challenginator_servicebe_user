package com.synclab.challenginatorUserService.appuser;


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
public class ValutationRequest {

    private final Long userId;

}
