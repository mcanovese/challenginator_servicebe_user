package com.synclab.challenginatorUserService.signin;

import com.synclab.challenginatorUserService.appuser.AppUser;
import com.synclab.challenginatorUserService.appuser.AppUserService;
import io.jsonwebtoken.JwtBuilder;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.apache.catalina.mbeans.UserMBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.JwtBuilder;


@RestController
@RequestMapping(path="/user/sign-in")
@AllArgsConstructor
public class SignInController {

    @Autowired
    private AuthenticationManager authenticationManager;

    private AppUserService appUserService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<?> createAuthenticationToken(@RequestBody SignInRequest signInRequest) throws Exception{

        try {
            authenticationManager.authenticate(  //prendo dalla request le credenziali e genero un nuovo oggetto
                    new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword())
            );
        } catch (BadCredentialsException e){
            throw new Exception("error bad username or password",e);
        }

       final UserDetails userDetails = appUserService.loadUserByUsername(
                signInRequest.getEmail());

        final String jwt = jwtUtil.generateToken(userDetails); // Genero un JWT
        return ResponseEntity.ok(new SignInResponse(jwt));


    }

}
