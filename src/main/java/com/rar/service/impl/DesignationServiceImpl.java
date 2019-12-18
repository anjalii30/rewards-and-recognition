package com.rar.service.impl;

import com.rar.model.Designation;
import com.rar.repository.DesignationRepository;
import com.rar.service.DesignationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DesignationServiceImpl implements DesignationService {

    @Autowired
    private DesignationRepository designationRepository;

    @Override
    public Designation save(Designation designation) {
        return designationRepository.save(designation);
    }

    @Override
    public ResponseEntity<List<Designation>> findAll() {
        return new ResponseEntity<>((List<Designation>) designationRepository.findAll(),HttpStatus.OK);
    }

    @Override
    public void deleteById(long id) {

        designationRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<Designation> findById(Long id) {
        return new ResponseEntity(designationRepository.findById(id), HttpStatus.OK);
    }

}
