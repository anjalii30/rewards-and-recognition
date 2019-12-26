package com.rar.service.impl;

import com.rar.model.Criteria;
import com.rar.repository.CriteriaRepository;
import com.rar.service.CriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CriteriaServiceImpl implements CriteriaService {

    @Autowired
    private CriteriaRepository criteriaRepository;

    @Override
    public ResponseEntity<Criteria> saveCriteria(Criteria criteria) {
        return ResponseEntity.ok(criteriaRepository.save(criteria));
    }

    @Override
    public ResponseEntity<List<Criteria>> findAll() {
        return new ResponseEntity<>((List<Criteria>) criteriaRepository.findAll(),HttpStatus.OK);
    }

    @Override
    public ResponseEntity deleteById(long id) {

        criteriaRepository.deleteById(id);
       return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Criteria> findById(Long id) {
        return new ResponseEntity(criteriaRepository.findById(id),HttpStatus.OK);
    }
}
