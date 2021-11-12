package com.synclab.challenginatorUserService.appuser;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/*
definizione del repository degli utenti, unico in questo microservizio
estende JPARespository, si fa uso di query predefinite (utilizzate nel service)
a questo repository si appoggiano i tre service previsti (quello generale - quello per il signup e signin)

 */

@Repository
@Transactional (readOnly = true)
public interface UserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);

}
