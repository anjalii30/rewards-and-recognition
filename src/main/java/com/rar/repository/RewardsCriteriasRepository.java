package com.rar.repository;

import com.rar.model.RewardsCriterias;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RewardsCriteriasRepository extends CrudRepository<RewardsCriterias, Long> {

    @Query(value="SELECT * from rewards_criterias where Reward_Id = ?1 ", nativeQuery = true)
    Set<RewardsCriterias> findByRewardId(Long rewardId);

}
