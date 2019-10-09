package com.rar.repository;

import com.rar.model.Criterias;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CriteriaRepository extends CrudRepository<Criterias,Long> {
}
