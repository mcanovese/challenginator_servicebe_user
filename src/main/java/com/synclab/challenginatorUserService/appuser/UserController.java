package com.synclab.challenginatorUserService.appuser;


import com.synclab.challenginatorUserService.signin.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController

public class UserController {

    private final AppUserService appUserService;

    @Autowired
    private  JwtUtil jwtUtil;


    public UserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    // POST USER delegata al signup controller

    @GetMapping("/user")
    public List<AppUser> getUser() {
        return appUserService.getAllUser();
    }

    @PutMapping("user")
    public AppUser updateUser(@RequestBody AppUser appUser){
        return appUserService.saveUser(appUser);
    }

    @GetMapping("user/{id}")
    public Optional<AppUser> getUserById(@PathVariable Long id ) {
        return appUserService.findById(id);
    }

    @PostMapping("user/authcheck")
    public UserDetails userAuthCheck(@RequestHeader(name= "Authorization") String jwt) {
        String username = jwtUtil.extractUsername(jwt);
        UserDetails userDetails = appUserService.loadUserByUsername(username);
        return userDetails;
    }

    @GetMapping("user/principal")
    public String principal(Principal principal){
        return principal.getName();
    }


}
