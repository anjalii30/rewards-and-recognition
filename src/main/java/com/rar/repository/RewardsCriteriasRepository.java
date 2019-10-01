package com.rar.repository;

import com.rar.model.RewardsCriteria;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RewardsCriteriasRepository extends CrudRepository<RewardsCriteria, Long> {

    @Query(value="SELECT * from rewards_criteria where reward_id = ?1 ", nativeQuery = true)
    Set<RewardsCriteria> findByRewardId(Long rewardId);

}
