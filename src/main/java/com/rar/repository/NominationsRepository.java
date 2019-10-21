package com.rar.repository;

import com.rar.model.Nominations;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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



    @Query(value="select * from nominations where user_id=?1 and reward_id=?2",nativeQuery = true)
    List<Nominations> getNominations(Long user_id,Long reward_id);

    @Transactional
    @Modifying
    @Query(value="update nominations set hr_selected=true where nomination_id=?1",nativeQuery = true)
    void awardeeSelect(Long nomination_id);

    @Query(value="select nominations.employee_name,nominations.project_name,nominations.reward_name, users.image_url from nominations,users where nominations.user_id=users.user_id and hr_selected=true",nativeQuery = true)
    List<Map<String,String>> getAwarded();

    @Query(value="select * from nominations where user_id=?1",nativeQuery = true)
    List<Nominations> getAllNominations(Long user_id);

    @Query(value="select * from nominations where reward_id=?1 ",nativeQuery = true)
    Optional<Nominations> findByRewardId(Long rewardID);

    @Transactional
    @Modifying
    @Query(value="update nominations set selected=true where nomination_id=?1",nativeQuery = true)
    void updateSelected(Long nomination_id);

   @Query(value="select * from nominations where selected=true",nativeQuery = true)
    List<Nominations> getAllNominations();

   @Query(value = "select distinct reward_id,reward_name from nominations",nativeQuery = true)
   List<Map<String, String>> nominated_rewards();




}
