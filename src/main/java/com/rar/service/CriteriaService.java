package com.rar.service;

import com.rar.model.Criteria;
import java.util.List;
import java.util.Optional;

public interface CriteriaService {

    Optional<Criteria> findById(Long id);

    Criteria saveCriteria(Criteria criteria);

    List<Criteria> findAll();

    void deleteById(long id);
}
