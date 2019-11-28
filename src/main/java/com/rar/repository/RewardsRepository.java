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

    @Query(value = "select * from rewards where award_status=1 and self_nominate=true and reward_id not in(select reward_id from nominations where user_id=?1) ",nativeQuery = true)
    List<Rewards> findByRolledForEmp(Long user_id);

    @Query(value = "Select criteria.criteria_desc from rewards_criteria join rewards on rewards_criteria.reward_id=rewards.reward_id join criteria on rewards_criteria.criteria_id=criteria.criteria_id where rewards.reward_id=?1", nativeQuery=true)
    List<Criteria> getCriteria(Long id);

    @Transactional
    @Modifying
    @Query(value = "Update rewards set regenerated=false where reward_id=?1", nativeQuery = true)
    void updateToNull(long id);

    @Query(value = " Select * from rewards where award_status=1 and self_nominate=1  and reward_id not in(select reward_id from nominations where user_id=?1) Order by start_date DESC limit 6",nativeQuery = true)
    List<Rewards> latest(Long user_id);

   @Query(value="select * from rewards where award_status=1 and self_nominate=true and reward_id not in(select reward_id from nominations where selected=true)",nativeQuery = true)
   List<Rewards> managerApprovalRewards();

    @Query(value = "select * from rewards where reward_id not in(select distinct reward_id from nominations where hr_selected=true)",nativeQuery = true)
   List<Rewards> nominated_rewards();

    @Query(value = "select distinct reward_id from nominations where reward_id in(select reward_id from rewards where self_nominate=true)",nativeQuery = true)
    Long[] rewardIds();

    @Query(value="select * from rewards where self_nominate=true and  reward_id in(select reward_id from nominations where user_id=?1 )",nativeQuery = true)
    List<Rewards> manager(Long user_id);

    @Query(value="select * from rewards where reward_id=?1",nativeQuery = true)
    List<Rewards> getReward(Long reward_id);

    @Query(value="select * from rewards order by reward_id desc",nativeQuery = true)
    List<Rewards> getAll();
}
