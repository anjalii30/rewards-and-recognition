package com.rar.repository;

import com.rar.model.Criteria;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CriteriaRepository extends CrudRepository<Criteria,Long> {
}
