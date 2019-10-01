package com.rar.service;


import com.rar.model.RewardsCriterias;

import java.util.List;

public interface RewardsCriteriasService {

  /*  Optional<RewardsCriterias> findById(Long rewardId);*/

    RewardsCriterias save(RewardsCriterias rewardsCriterias);


    List<RewardsCriterias> findAll();

 /*   void deleteById(long id);*/
}
