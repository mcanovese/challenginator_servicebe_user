package com.synclab.challenginatorUserService.appuser;

import lombok.AllArgsConstructor;
import org.h2.util.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final UserRepository userRepository;  //Repository utenti per accesso al DB
    private final BCryptPasswordEncoder bCryptPasswordEncoder; //Encrypter password

    //Metodo per registrazione utente, verifica se c'è già la mail nel DB e inserisce
    public HttpStatus signUpUser(AppUser appUser){
    boolean userExists = userRepository.findByEmail(appUser.getEmail())
            .isPresent();

    if (userExists) { throw new IllegalStateException("email already in db");}

    String cryptPwd = bCryptPasswordEncoder.encode(appUser.getPassword());
    appUser.setPassword(cryptPwd);

    userRepository.save(appUser);
        return HttpStatus.OK;
    }

    public List<AppUser> getAllUser(){
        return userRepository.findAll();
    }

    public HttpStatus saveUser(AppUser appUser){
        AppUser user =  userRepository.save(appUser);
        return HttpStatus.OK;
    }

    public Optional<AppUser> findById(Long id){
        return userRepository.findById(id);
    }

    public Map<String, Long> getBoss(Long id){
        Map<String,Long> bossDetails = new HashMap<>();

        AppUser currentUSer = userRepository.getById(id);
        AppUser bossOfCurrent = userRepository.getById(currentUSer.getBossId());

        //Non posso sfidare un Admin
        if(bossOfCurrent.getAppUserRole() != AppUserRole.ADMIN){
        AppUser bossOfMyBoss = userRepository.getById(bossOfCurrent.getBossId());
        bossDetails.put("bossOfUserBoss",bossOfMyBoss.getId());
        } else  bossDetails.put("bossOfUserBoss", null);

        bossDetails.put("userId",currentUSer.getId());
        bossDetails.put("bossOfUser",bossOfCurrent.getId());

        return bossDetails;
    }


    @Override
    public AppUser loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(
                ()-> new UsernameNotFoundException("ERROR - email not found"));
    }

}
