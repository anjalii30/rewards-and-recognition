package com.rar.repository;

import com.rar.model.Nominations;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface NominationsRepository extends CrudRepository<Nominations, String> {

    @Query(value = "select nomination_id from nominations where user_id=?1, reward_id=?2", nativeQuery = true)
    public long getNominationId(String user_id, long reward_id);


    @Query(value = "update nominations set disable=true where user_id=?1 ",nativeQuery = true)
    void setDisable(Long user_id);


    /*@Query(value = "select * from rewards where reward_id in(select reward_id from nominations where disable=false )",nativeQuery = true)
    List<Rewards> getRewards();
*/

    @Query(value = "select * from nominations where reward_id=?1",nativeQuery = true)
    List<Nominations> GetData(Long rewardID);

    @Query(value="select user_id from user_manager where manager_id=?1 ",nativeQuery = true)
    Long[] getMembers(Long manager_id);

    @Query(value="select * from nominations where user_id=?1",nativeQuery = true)
    List<Nominations> getNominations(Long user_id);

    @Transactional
    @Modifying
    @Query(value="update nominations set hr_selected=true where nomination_id=?1",nativeQuery = true)
    void awardeeSelect(Long nomination_id);
}
