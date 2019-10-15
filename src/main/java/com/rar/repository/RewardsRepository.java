package com.rar.repository;

import com.rar.model.Criteria;
import com.rar.model.Rewards;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RewardsRepository extends CrudRepository<Rewards, Long> {

 
    @Query(value = "select * from rewards where award_status=1 and reward_id not in(select reward_id from nominations where user_id=?1) ",nativeQuery = true)
    List<Rewards> findByRolled(Long user_id);


    @Query(value = "Select criteria.criteria_desc from rewards_criteria join rewards on rewards_criteria.reward_id=rewards.reward_id join criteria on rewards_criteria.criteria_id=criteria.criteria_id where rewards.reward_id=?1", nativeQuery=true)
    List<Criteria> getCriteria(Long id);

    @Transactional
    @Modifying
    @Query(value = "Update rewards set regenerated=false where reward_id=?1", nativeQuery = true)
    void updateToNull(long id);





}
