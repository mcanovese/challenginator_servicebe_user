package com.synclab.challenginatorUserService.security.config;

import com.synclab.challenginatorUserService.appuser.AppUserService;
import com.synclab.challenginatorUserService.appuser.UserRepository;
import com.synclab.challenginatorUserService.signin.JwtUtil;
import com.synclab.challenginatorUserService.signup.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
public class AppConfig {

    @Bean
    public JwtUtil getJwtUtil(){
        return new JwtUtil();
    }

}
