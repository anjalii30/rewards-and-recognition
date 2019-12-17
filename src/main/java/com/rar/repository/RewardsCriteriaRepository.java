package com.rar.repository;

import com.rar.entity.RewardsCriteria;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;

@Repository
public interface RewardsCriteriaRepository extends CrudRepository<RewardsCriteria, Long> {

    @Query(value="SELECT * from rewards_criteria where reward_id = ?1 ", nativeQuery = true)
    Set<RewardsCriteria> findByRewardId(Long rewardId);

    @Query(value="Select * from rewards_criteria where reward_id= ?1 and criteria_id= ?2", nativeQuery = true)
    Set<RewardsCriteria> findById(Long rid, Long cid);

    @Transactional
    @Modifying
    @Query(value = "delete from rewards_criteria where reward_id = ?1 and criteria_id = ?2", nativeQuery = true)
    void deleteById(Long rid, Long cid);

    @Modifying
    @Transactional
    @Query(value="insert into rewards_criteria (criteria_id,reward_id,is_compulsory) values (:criteria_id, :reward_id, :compulsory)",nativeQuery = true)
    void insertById( Long criteria_id, Long reward_id, Boolean compulsory);

}
