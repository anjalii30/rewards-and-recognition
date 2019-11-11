package com.rar.service.impl;

import com.rar.model.Criteria;
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
    public Criteria saveCriteria(Criteria criteria) {
        return criteriaRepository.save(criteria);
    }

    @Override
    public List<Criteria> findAll() {
        return (List<Criteria>) criteriaRepository.findAll();
    }

    @Override
    public void deleteById(long id) {

        criteriaRepository.deleteById(id);
    }

    @Override
    public Optional<Criteria> findById(Long id) {
        return criteriaRepository.findById(id);
    }

}
