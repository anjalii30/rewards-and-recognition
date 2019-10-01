package com.rar.service.impl;


import com.rar.model.Nominations;
import com.rar.repository.NominationsRepository;
import com.rar.service.NominationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NominationsServiceImpl implements NominationsService {

    @Autowired
    NominationsRepository nominationsRepository;


    @Override
    public Nominations save(Nominations nominations) {
        return nominationsRepository.save(nominations);
    }

    @Override
    public List<Nominations> findAll() {
        return (List<Nominations>) nominationsRepository.findAll();
    }

    @Override
    public void deleteById(long id) {

        nominationsRepository.deleteById(id);
    }

    @Override
    public Optional<Nominations> findById(Long id) {
        return nominationsRepository.findById(id);
    }
}
