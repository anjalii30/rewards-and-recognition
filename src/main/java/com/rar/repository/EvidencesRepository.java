package com.rar.repository;

import com.rar.model.Evidences;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EvidencesRepository extends CrudRepository<Evidences, List> {
}
