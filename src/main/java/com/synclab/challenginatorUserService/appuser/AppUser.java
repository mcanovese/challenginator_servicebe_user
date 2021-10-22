package com.synclab.challenginatorUserService.appuser;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

/*
Definizione della classe AppUser (ricordare che User è una keyword riservata di postgres per cui da errore se si mappa una classe User)
estende UserDetails per integrazione con Spring Security
 */


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class AppUser implements UserDetails {

    @Id  // PK di AppUser nel DB - generazione automatica e sequenziale dell' id utente
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "user_sequence")
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Long bossId;
    private Long score;
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;  // definito tipo enum solo con ADMIN e USER
    private Boolean locked = false;  // non utilizzati nella logica implementativa
    private Boolean enabled = true;

    public AppUser(String name,
                   String surname,
                   String email,
                   String password,
                   Long bossId,
                   Long score,
                   AppUserRole appUserRole
                 ) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.bossId = bossId;
        this.score = score;
        this.appUserRole = appUserRole;

    }

    // ovveride dei metodi di UserDetails per poterla usare

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singletonList(authority);
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }  //ritorna sempre true - attributo non utilizzato

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Long getId() {return id;}


}
