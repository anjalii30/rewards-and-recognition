package com.rar.service;

import com.rar.entity.Criteria;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CriteriaService {

    ResponseEntity<Criteria> findById(Long id);

    ResponseEntity<Criteria> saveCriteria(Criteria criteria);

    ResponseEntity<List<Criteria>>findAll();

    ResponseEntity<String> deleteById(long id);
}
