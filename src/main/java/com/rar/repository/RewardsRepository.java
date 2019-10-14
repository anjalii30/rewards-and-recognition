package com.rar.repository;

import com.rar.model.Criterias;
import com.rar.model.Rewards;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RewardsRepository extends CrudRepository<Rewards, Long> {


    @Query(value = "SELECT * FROM rewards WHERE award_status = 1",
            nativeQuery=true)
    List<Rewards> findByRolled();

    @Query(value = "SELECT * FROM rewards WHERE award_status = 2",
            nativeQuery=true)
    List<Rewards> findByNominationClosed();

    @Query(value = "SELECT * FROM rewards WHERE award_status = 3",
            nativeQuery=true)
    List<Rewards> findByDiscontinued();





    @Query(value = "Select criteria.criteria_desc from rewards_criteria join rewards on rewards_criteria.reward_id=rewards.reward_id join criteria on rewards_criteria.criteria_id=criteria.criteria_id where rewards.reward_id=?1", nativeQuery=true)
    List<Criterias> getCriteria(Long id);

    @Transactional
    @Modifying
    @Query(value = "Update rewards set regenerated=false where reward_id=?1", nativeQuery = true)
    void updateToNull(long id);





}
