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
    long getNominationId(String userId, long rewardId);

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

    @Query(value="select nominations.user_name,nominations.project_name,nominations.reward_name, users.image_url,designation.designation from nominations,users,designation where nominations.user_id=users.user_id and hr_selected=true and designation_id in(select designation_id from user_designation where user_id=nominations.user_id) ",nativeQuery = true)
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

    @Query(value="select nominations.user_name,nominations.project_name,nominations.reward_name, users.image_url,designation.designation from nominations,users,designation where nominations.user_id=users.user_id and hr_selected=true and designation_id in(select designation_id from user_designation where user_id=nominations.user_id) Order by nomination_id DESC limit 6",nativeQuery = true)
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

    @Query(value = "select distinct reward_name from nominations where reward_id=?1", nativeQuery = true)
    String rewardName(long rewardId);

    @Query(value = "select distinct reward_id from nominations where manager_id=?1", nativeQuery = true)
    long[] rewardId(long managerId);

    @Query(value = "select user_id from nominations where manager_id=?1 and reward_id=?2 and project_id=?3", nativeQuery = true)
    long[] userIds(long managerId, long rewardId, long projectId);

    @Query(value = "select reason from nominations where manager_id =?1 and reward_id =?2 and user_id=?3 and project_id=?4", nativeQuery = true)
    String gettingReason(long managerId, long rewardId, long userId, long projectId);

    @Query(value = "select selected from nominations where manager_id =?1 and reward_id =?2 and user_id=?3 and project_id=?4", nativeQuery = true)
    Boolean gettingSelected(long managerId, long rewardId, long userId, long projectId);

    @Query(value="select distinct reward_id from nominations where user_id=?1",nativeQuery=true)
    Long[] getRewardIdForUser(Long userId);

    @Query(value = "select count(nomination_id) from nominations where reward_id=?1 and hr_selected=true",nativeQuery = true)
    Long getCount(Long rewardId);

    @Query(value = "select distinct project_id from nominations where manager_id=?1 and reward_id=?2",nativeQuery = true)
    long[] getProjectIds(long manager_id,long reward_id);
}
