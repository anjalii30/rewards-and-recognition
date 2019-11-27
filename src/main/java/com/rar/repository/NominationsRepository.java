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

    @Query(value = "select * from nominations where reward_id=?1",nativeQuery = true)
    List<Nominations> GetData(Long rewardID);

    @Query(value="select * from nominations where user_id=?1 and reward_id=?2",nativeQuery = true)
    List<Nominations> getNominations(Long user_id,Long reward_id);

    @Transactional
    @Modifying
    @Query(value="update nominations set hr_selected=true where nomination_id=?1",nativeQuery = true)
    void awardeeSelect(Long nomination_id);

    @Query(value="select nominations.user_name,nominations.project_name,nominations.reward_name, users.image_url from nominations,users where nominations.user_id=users.user_id and hr_selected=true ",nativeQuery = true)
    List<Map<String,String>> getAwarded();

    @Query(value="select * from nominations where user_id=?1",nativeQuery = true)
    List<Nominations> getAllNominations(Long user_id);

    @Query(value="select * from nominations where reward_id=?1 ",nativeQuery = true)
    Optional<Nominations> findByRewardId(Long rewardID);

    @Transactional
    @Modifying
    @Query(value="update nominations set selected=?1 , reason=?2 , manager_id=?4 , approved_by=?5 where nomination_id=?3",nativeQuery = true)
    void updateSelected(boolean selected,String reason,Long nomination_id,Long manager_id,String manager_name );

   @Query(value="select * from nominations where selected=true",nativeQuery = true)
   List<Nominations> getAllNominations();

    @Query(value="select nominations.user_name,nominations.reward_name, users.image_url from nominations,users where nominations.user_id=users.user_id and hr_selected=true Order by nomination_id DESC limit 6",nativeQuery = true)
    List<Map<String, String>> getTopAwardee();

    @Query(value="select * from nominations where user_id=?1",nativeQuery = true)
    List<Nominations> exist(Long user_id);
}
