package com.synclab.challenginatorUserService.appuser;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final UserRepository userRepository;  //Repository utenti per accesso al DB
    private final BCryptPasswordEncoder bCryptPasswordEncoder; //Encrypter password

    //Metodo per registrazione utente, verifica se c'è già la mail nel DB e inserisce
    public String signUpUser(AppUser appUser){
    boolean userExists = userRepository.findByEmail(appUser.getEmail())
            .isPresent();

    if (userExists) { throw new IllegalStateException("email already in db");}

    String cryptPwd = bCryptPasswordEncoder.encode(appUser.getPassword());
    appUser.setPassword(cryptPwd);
    

    userRepository.save(appUser);
            return "success";
    }

    public List<AppUser> getAllUser(){
        return userRepository.findAll();
    }

    public AppUser saveUser(AppUser appUser){
        return userRepository.save(appUser);
    }

    public Optional<AppUser> findById(Long id){
        return userRepository.findById(id);
    }



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(
                ()-> new UsernameNotFoundException("ERROR - email not found"));
    }
}
