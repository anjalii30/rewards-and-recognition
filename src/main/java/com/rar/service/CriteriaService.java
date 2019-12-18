package com.rar.service;

import com.rar.model.Criteria;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CriteriaService {

    ResponseEntity<Criteria> findById(Long id);

    ResponseEntity<Criteria> saveCriteria(Criteria criteria);

    ResponseEntity<List<Criteria>>findAll();

    ResponseEntity<String> deleteById(long id);
}
