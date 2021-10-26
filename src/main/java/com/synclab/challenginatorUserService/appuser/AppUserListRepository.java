package com.synclab.challenginatorUserService.appuser;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

// il repository si occupa di dialogare con il DB postgres, estende JPARespository

@Repository
public interface AppUserListRepository extends JpaRepository<AppUser,Long> {


    @Query("SELECT a.id as id, a.name as name, a.surname as surname FROM AppUser a")
    List<AppUserList> findAllAppUser();


}
