package com.synclab.challenginatorUserService.security.config;


import com.synclab.challenginatorUserService.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



/*
Configurazione Spring Security
gli endpoint di sign-up e sign-in sono ad accesso libero
agli altri viene applicato il filtro JWT per verificare token
il tutto è STATELESS in quanto non viene aperta una session ma ogni richiesta viene verificata
 */


@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private final AppUserService appUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

   @Autowired
   private JwtFilter jwtFilter;

   @Autowired
   private ExceptionHandlerFilter exceptionHandlerFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http
               .csrf().disable()
               .authorizeRequests()
               .antMatchers("/user/sign-up/**", "/user/sign-in/**", "/user/authcheck/**","/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**")
               .permitAll()
               .anyRequest()
               .authenticated()
               .and().sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS);  // imposto policy stateless su session, ed integro il filtro JWT
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        http.addFilterBefore(exceptionHandlerFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(exceptionHandlerFilter, JwtFilter.class);


    }



    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;
    }

     //OVVERIDE NECESSARIO per poter marcare con @Autowired l' AuthenticationManager nel service del SignIn
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }



}
