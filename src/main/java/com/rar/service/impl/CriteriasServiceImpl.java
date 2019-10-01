package com.rar.service.impl;

import com.rar.model.Criterias;
import com.rar.repository.CriteriasRepository;
import com.rar.service.CriteriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CriteriasServiceImpl implements CriteriasService {

    @Autowired
    CriteriasRepository criteriasRepository;


    @Override
    public Criterias saveCriteria(Criterias criterias) {
        return criteriasRepository.save(criterias);
    }

    @Override
    public List<Criterias> findAll() {
        return (List<Criterias>) criteriasRepository.findAll();
    }

    @Override
    public void deleteById(long id) {

        criteriasRepository.deleteById(id);
    }

    @Override
    public Optional<Criterias> findById(Long id) {
        return criteriasRepository.findById(id);
    }

}
