package com.rar.service;

import com.rar.model.Criterias;

import java.util.List;
import java.util.Optional;

public interface CriteriaService {


    Optional<Criterias> findById(Long id);

    Criterias saveCriteria(Criterias criterias);


    List<Criterias> findAll();

    void deleteById(long id);
}
