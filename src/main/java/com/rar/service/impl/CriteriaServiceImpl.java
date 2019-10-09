package com.rar.service.impl;

import com.rar.model.Criterias;
import com.rar.repository.CriteriaRepository;
import com.rar.service.CriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CriteriaServiceImpl implements CriteriaService {

    @Autowired
    private CriteriaRepository criteriaRepository;


    @Override
    public Criterias saveCriteria(Criterias criterias) {
        return criteriaRepository.save(criterias);
    }

    @Override
    public List<Criterias> findAll() {
        return (List<Criterias>) criteriaRepository.findAll();
    }

    @Override
    public void deleteById(long id) {

        criteriaRepository.deleteById(id);
    }

    @Override
    public Optional<Criterias> findById(Long id) {
        return criteriaRepository.findById(id);
    }

}
