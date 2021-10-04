package com.synclab.challenginatorUserService.signin;

import com.synclab.challenginatorUserService.appuser.AppUser;
import com.synclab.challenginatorUserService.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SignInService {

    private final AppUserService appUserService;

    public String login(SignInRequest request){

        return appUserService.signInUser(
                request.getEmail(),
                request.getPassword()
        );


    }
}
