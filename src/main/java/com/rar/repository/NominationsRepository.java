package com.rar.repository;

import com.rar.model.Nominations;
import com.rar.model.Rewards;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NominationsRepository extends CrudRepository<Nominations, Long> {

    @Query(value = "select nomination_id from nominations where user_id=?1, rewardid=?2", nativeQuery = true)
    public long getNominationId(String user_id, long reward_id);


    @Query(value = "update nominations set disable=true where user_id=?1 ",nativeQuery = true)
    void setDisable(String user_id);


    @Query(value = "select * from rewards where reward_id in(select reward_id from nominations where disable=false )",nativeQuery = true)
    List<Rewards> getRewards();

}
