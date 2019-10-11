package com.rar.service;


import com.rar.model.RewardsCriteria;

import java.util.List;
import java.util.Set;

public interface RewardsCriteriaService {

  /*  Optional<RewardsCriteria> findById(Long rewardId);*/

    RewardsCriteria save(RewardsCriteria rewardsCriteria);

    Set<RewardsCriteria> findById(Long rid, Long cid);

    List<RewardsCriteria> findAll();

 /*   void deleteById(long id);*/
}
