package com.rar.repository;

import com.rar.model.EmployeeRelation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRelationRepository extends CrudRepository<EmployeeRelation, Long> {
}
