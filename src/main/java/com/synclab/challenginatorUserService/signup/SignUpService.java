package com.synclab.challenginatorUserService.signup;

import com.synclab.challenginatorUserService.appuser.AppUser;
import com.synclab.challenginatorUserService.appuser.AppUserRole;
import com.synclab.challenginatorUserService.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SignUpService  {

    private final AppUserService appUserService;

    public HttpStatus register(SignUpRequest request) {

        return appUserService.signUpUser(
                new AppUser(
                        request.getName(),
                        request.getSurname(),
                        request.getEmail(),
                        request.getPassword(),
                        122L,
                        100L,
                        AppUserRole.USER
                )
        );

    }
}
