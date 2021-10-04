package com.synclab.challenginatorUserService.appuser;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String signUpUser(AppUser appUser){
    boolean userExists = userRepository.findByEmail(appUser.getEmail())
            .isPresent();

    if (userExists) { throw new IllegalStateException("email already in db");}

    String cryptPwd = bCryptPasswordEncoder.encode(appUser.getPassword());
    appUser.setPassword(cryptPwd);

    userRepository.save(appUser);



            return "it works";
    }


    public String signInUser(String username, String password){
        return "login";
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(
                ()-> new UsernameNotFoundException("ERROR - email not found"));
    }
}
