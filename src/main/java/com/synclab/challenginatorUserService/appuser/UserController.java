package com.synclab.challenginatorUserService.appuser;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class UserController {

    @Autowired
    private final AppUserService appUserService;

    @Autowired
    private final UserRepository userRepository;

    public UserController(AppUserService appUserService, UserRepository userRepository) {
        this.appUserService = appUserService;
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







}
