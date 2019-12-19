package com.rar.repository;

import com.rar.model.Nominations;
import com.rar.model.UserInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface NominationsRepository extends CrudRepository<Nominations, String> {

    @Query(value = "select nomination_id from nominations where user_id=?1, reward_id=?2", nativeQuery = true)
    public long getNominationId(String userId, long rewardId);

    @Query(value = "update nominations set disable=true where user_id=?1 ",nativeQuery = true)
    void setDisable(Long userId);

    @Query(value = "select * from nominations where reward_id=?1",nativeQuery = true)
    List<Nominations> GetData(Long rewardID);

    @Query(value="select * from nominations where user_id=?1 and reward_id=?2",nativeQuery = true)
    List<Nominations> getNominations(Long userId,Long rewardId);

    @Transactional
    @Modifying
    @Query(value="update nominations set hr_selected=true where nomination_id=?1",nativeQuery = true)
    void awardeeSelect(Long nominationId);

    @Query(value="select nominations.user_name,nominations.project_name,nominations.reward_name, users.image_url from nominations,users where nominations.user_id=users.user_id and hr_selected=true ",nativeQuery = true)
    List<Map<String,String>> getAwarded();

    @Query(value="select * from nominations where user_id=?1",nativeQuery = true)
    List<Nominations> getAllNominations(Long userId);

    @Query(value="select * from nominations where reward_id=?1 ",nativeQuery = true)
    Optional<Nominations> findByRewardId(Long rewardID);

    @Transactional
    @Modifying
    @Query(value="update nominations set selected=?1 , reason=?2 , manager_id=?4 , approved_by=?5 where nomination_id=?3",nativeQuery = true)
    void updateSelected(boolean selected,String reason,Long nominationId,Long managerId,String managerName );

   @Query(value="select * from nominations where selected=true",nativeQuery = true)
   List<Nominations> getAllNominations();

   @Query(value="select nominations.user_name,nominations.reward_name, users.image_url from nominations,users where nominations.user_id=users.user_id and hr_selected=true Order by nomination_id DESC limit 6",nativeQuery = true)
    List<Map<String, String>> getTopAwardee();

    @Query(value="select * from nominations where user_id=?1",nativeQuery = true)
    List<Nominations> exist(Long userId);

    @Query(value="select * from users where user_id in(select user_id from nominations where nomination_id=?1)",nativeQuery = true)
    UserInfo getUserDetails(Long nominationId);

    @Query(value="select reward_name from rewards where reward_id in(select reward_id from nominations where nomination_id=?1)",nativeQuery = true)
    String getRewardName(Long nominationId);

    @Query(value="select reward_id from nominations where nomination_id=?1",nativeQuery = true)
    Long getRewardId(Long nominationId);

    @Query(value="select name from users where user_id in(select user_id from nominations where nomination_id=?1)",nativeQuery = true)
    String getUserName(Long nominationId);

    @Query(value="select user_id from nominations where nomination_id=?1",nativeQuery = true)
    Long userId(Long nominationId);

    @Query(value="select count(nomination_id) from nominations where project_id=?1 and manager_id=?2 and reward_id=?3",nativeQuery = true)
    Long checkIfExists(Long projectId, Long managerId, Long rewardId);
}
