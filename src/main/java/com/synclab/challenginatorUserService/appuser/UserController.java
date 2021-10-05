package com.synclab.challenginatorUserService.appuser;


import com.synclab.challenginatorUserService.signin.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class UserController {

    @Autowired
    private final AppUserService appUserService;

    @Autowired
    private final JwtUtil jwtUtil;

    @Autowired
    private final UserRepository userRepository;

    public UserController(AppUserService appUserService, JwtUtil jwtUtil, UserRepository userRepository) {
        this.appUserService = appUserService;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    // POST USER delegata al signup controller

    @GetMapping("/user")
    public List<AppUser> getUser() {
        return userRepository.findAll();
    }

    @PutMapping("user")
    public AppUser updateUser(@RequestBody AppUser appUser){
        return userRepository.save(appUser);
    }

    @GetMapping("user/{id}")
    public Optional<AppUser> getUserById(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    @PostMapping("user/authcheck")
    public Boolean userAuthCheck(@RequestHeader(name= "Authorization") String jwt) {
        return !jwtUtil.isTokenExpired(jwt);
    }



}
