package com.rar.service;


import com.rar.model.RewardsCriteria;

import java.util.List;

public interface RewardsCriteriaService {

  /*  Optional<RewardsCriteria> findById(Long rewardId);*/

    RewardsCriteria save(RewardsCriteria rewardsCriteria);


    List<RewardsCriteria> findAll();

 /*   void deleteById(long id);*/
}