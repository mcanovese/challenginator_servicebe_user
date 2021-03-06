package com.synclab.challenginatorUserService.signin;

import com.synclab.challenginatorUserService.appuser.AppUser;
import com.synclab.challenginatorUserService.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


/*
Controller REST che gestisce le operazioni di autenticazione
 */

@RestController
@RequestMapping(path="/user/sign-in")
@AllArgsConstructor
public class SignInController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    private AppUserService appUserService;


    @PostMapping
    public ResponseEntity<?> createAuthenticationToken(@RequestBody SignInRequest signInRequest) throws RuntimeException {

        try {                                   //prendo dalla request le credenziali e genero un nuovo oggetto
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"error\":\"bad credential\"}");
        }


        final UserDetails userDetails = appUserService.loadUserByUsername(
                signInRequest.getEmail());

        final String jwt = jwtUtil.generateToken(userDetails); // Genero un JWT
        final String userId = ((AppUser) userDetails).getId().toString();

        return ResponseEntity.ok(new SignInResponse(jwt,userId));

    }

}
