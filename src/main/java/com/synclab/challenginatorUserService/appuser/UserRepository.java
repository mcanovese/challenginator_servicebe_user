package com.synclab.challenginatorUserService.appuser;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

// il repository si occupa di dialogare con il DB postgres, estende JPARespository

@Repository
@Transactional (readOnly = true)
public interface UserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);

}
