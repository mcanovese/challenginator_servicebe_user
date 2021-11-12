package com.synclab.challenginatorUserService.appuser;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/*
respository definito per estrarre AppUserList ovvero query limitata ai dati necessari da ritoranre al FE per formazione
della select di selezione utente da sfidare
 */

@Repository
public interface AppUserListRepository extends JpaRepository<AppUser,Long> {


    @Query("SELECT a.id as id, a.name as name, a.surname as surname FROM AppUser a")
    List<AppUserList> findAllAppUser();


}
