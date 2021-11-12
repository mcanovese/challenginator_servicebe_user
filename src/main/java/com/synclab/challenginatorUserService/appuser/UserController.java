package com.synclab.challenginatorUserService.appuser;

import com.synclab.challenginatorUserService.signin.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;


/*
REST controller che intercetta la maggior parte delle richieste.
si occupa di tutto tranne che di signup e signin
 */


@RestController
public class UserController {

    private final AppUserService appUserService;
    private final AppUserListRepository appUserListRepository;


    @Autowired
    private  JwtUtil jwtUtil;

    public UserController(AppUserService appUserService, AppUserListRepository appUserListRepository ) {
        this.appUserService = appUserService;
        this.appUserListRepository = appUserListRepository;
    }

    // POST USER delegata al signup controller


    //GET - tutti gli utenti
    @GetMapping("/user/list")
    public List<AppUserList> getUserList() {
        return appUserService.getUserList();
    }


    //PUT - aggiorna utente
    @PutMapping("user")
    public HttpStatus updateUser(@RequestBody AppUser appUser){
        return appUserService.saveUser(appUser);
    }

    //GET - ritorna informazioni utente da ID
    @GetMapping("user/{id}")
    public Optional<AppUser> getUserById(@PathVariable Long id ) {
        return appUserService.findById(id);
    }

    //GET - restituisce la gerarchia di responsabili di un id
    @GetMapping("user/valutator/{id}")
    public Map<String,Long> getValutator(@PathVariable Long id ) {
        return appUserService.getBoss(id);
    }

    //POST - endpoint dedicato alla valutazione di una sfida
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

    //POST - verifica se l'utente è autenticato, in caso affermativo si ritorna l'ID utente
    @PostMapping("user/authcheck")
    public Long userAuthCheck(@RequestHeader(name= "Authorization") String jwt) {
        String username = jwtUtil.extractUsername(jwt);
        AppUser userDetails = appUserService.loadUserByUsername(username);
        return userDetails.getId();
    }
}
