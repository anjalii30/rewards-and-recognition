package com.rar.service;


import com.rar.model.Nominations;

import java.util.List;
import java.util.Optional;

public interface NominationsService {

    Optional<Nominations> findById(Long id);

    Nominations save(Nominations nominations);


    List<Nominations> findAll();

    void deleteById(long id);



}


