package com.synclab.challenginatorUserService.appuser;

import javax.persistence.Entity;


/*
Interfaccia AppUserList per la definizione dei dati da ritornare come AppUserList
viene utilizzata per rispondere all'API che richiede l'elenco di tutti gli utenti
fatta appositamente per evitare di dover ritornare anche dati non necessari alla formazione della "select" nel front end.
 */

public interface AppUserList {

    Long getId();
    String getName();
    String getSurname();

}
