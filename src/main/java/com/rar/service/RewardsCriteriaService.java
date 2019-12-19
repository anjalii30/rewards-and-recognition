package com.rar.service;

import com.rar.model.RewardsCriteria;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface RewardsCriteriaService {

    ResponseEntity<RewardsCriteria> save(RewardsCriteria rewardsCriteria);

    Set<RewardsCriteria> findById(Long rid, Long cid);

    ResponseEntity<List<RewardsCriteria>> findAll();

    void deleteById(Long rid, Long cid);


}
