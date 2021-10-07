package com.synclab.challenginatorUserService.signup;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user/sign-up")
public class SignUpController {

    private SignUpService signUpService;

    @PostMapping
    public String register(@RequestBody  SignUpRequest request){
        return signUpService.register(request);
    }



}
