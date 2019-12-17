package com.rar.service;

import com.rar.entity.Criteria;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CriteriaService {

    Optional<Criteria> findById(Long id);

    ResponseEntity<Criteria> saveCriteria(Criteria criteria);

    List<Criteria>findAll();

    ResponseEntity<String> deleteById(long id);
}
