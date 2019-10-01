package com.rar.service.impl;


import com.rar.model.Designation;
import com.rar.repository.DesignationRepository;
import com.rar.service.DesignationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DesignationServiceImpl implements DesignationService {
    //logger
    @Autowired
    DesignationRepository designationRepository;


    @Override
    public Designation save(Designation designation) {
        return designationRepository.save(designation);
    }

    @Override
    public List<Designation> findAll() {
        return (List<Designation>) designationRepository.findAll();
    }

    @Override
    public void deleteById(long id) {

        designationRepository.deleteById(id);
    }

    @Override
    public Optional<Designation> findById(Long id) {
        return designationRepository.findById(id);
    }


}
