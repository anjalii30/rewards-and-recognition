package com.rar.repository;

import com.rar.model.Criterias;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CriteriasRepository extends CrudRepository<Criterias,Long> {
}
