package com.synclab.challenginatorUserService.signup;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/*
REST Controller che si occupa di gestire le richieste legate al processo di registrazione nuovo utente
 */

@RestController
@RequestMapping(path = "/user/sign-up")
@AllArgsConstructor
public class SignUpController {

    private SignUpService signUpService;

    @PostMapping
    public HttpStatus register(@RequestBody  SignUpRequest request){
        if(request.requestCheck()) return signUpService.register(request);
        else   return HttpStatus.OK;
    }



}
