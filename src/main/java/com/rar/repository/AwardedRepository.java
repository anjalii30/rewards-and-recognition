package com.rar.repository;

import com.rar.model.Awarded;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AwardedRepository extends CrudRepository<Awarded, Long> {

    @Query(value="select users.name, users.image_url,projects.project_name,rewards.reward_name from users,projects,rewards,awarded where awarded.employee_id=users.user_id and projects.project_id=awarded.project_id  and rewards.reward_id=awarded.reward_id",nativeQuery = true)
    Object findAllAwarded();

   /* @Query(value = "SELECT  DISTINCT year from awarded ", nativeQuery = true)
    public List getAllYears();


    @Query(value = "SELECT  count(DISTINCT employee_id) from awarded where year = ?1  ", nativeQuery = true)
    public long findByPeople(Long year);

    @Query(value="SELECT count(reward_name) from  awarded where year = ?1 ", nativeQuery = true)
    public long findByRewards(Long year);
    @Query(value = "SELECT count(frequency) from awarded where frequency = 'Weekly' and year =?1", nativeQuery = true)
    public long weeklyFrequency(Long year);

    @Query(value = "SELECT count(frequency) from awarded where frequency = 'Monthly' and year =?1", nativeQuery = true)
    public long monthlyFrequency(Long year);

    @Query(value = "SELECT count(frequency) from awarded where frequency = 'Quarterly' and year =?1", nativeQuery = true)
    public long quarterlyFrequency(Long year);

    @Query(value = "SELECT count(frequency) from awarded where frequency = 'Annually' and year =?1", nativeQuery = true)
    public long annuallyFrequency(Long year);

    @Query(value = "SELECT count(frequency) from awarded where frequency = 'Spot' and year =?1", nativeQuery = true)
    public long spotFrequency(Long year);

*/
  /*  @Query(value="SELECT employee_image,reward_name,subject_description from awarded",nativeQuery = true)
    public List ehomepage();*/
}