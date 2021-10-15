package com.synclab.challenginatorUserService.appuser;

import com.synclab.challenginatorUserService.signin.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {

    private final AppUserService appUserService;


    @Autowired
    private  JwtUtil jwtUtil;

    public UserController(AppUserService appUserService ) {
        this.appUserService = appUserService;
    }

    // POST USER delegata al signup controller

    @GetMapping("/user")
    public List<AppUser> getUser() {
        return appUserService.getAllUser();
    }

    @PutMapping("user")
    public HttpStatus updateUser(@RequestBody AppUser appUser){
        return appUserService.saveUser(appUser);
    }

    @GetMapping("user/{id}")
    public Optional<AppUser> getUserById(@PathVariable Long id ) {
        return appUserService.findById(id);
    }

    @GetMapping("user/valutator/{id}")
    public Map<String,Long> getValutator(@PathVariable Long id )
    {
        //restituisce la gerarchia di responsabili di un id
        return appUserService.getBoss(id);
    }

    @PostMapping( "/user/valutate")
    public HttpStatus addPoint(@RequestHeader(name="challenged") Long challengedId,@RequestHeader(name= "Authorization") String jwt){

        Map<String,Long> authValutator = appUserService.getBoss(challengedId);
        String username = jwtUtil.extractUsername(jwt);
        AppUser valutator = appUserService.loadUserByUsername(username);

        if(valutator.getId()== authValutator.get("bossOfUser") || valutator.getId()== authValutator.get("bossOfUserBoss") )
        {
            Boolean result = appUserService.addPointToUser(challengedId);
            if(result) return HttpStatus.OK;
            else return HttpStatus.BAD_REQUEST;
        }
        else return HttpStatus.BAD_REQUEST;

    }

    @PostMapping("user/authcheck")
    public Long userAuthCheck(@RequestHeader(name= "Authorization") String jwt) {
        String username = jwtUtil.extractUsername(jwt);
        AppUser userDetails = appUserService.loadUserByUsername(username);
        return userDetails.getId();
    }
}
