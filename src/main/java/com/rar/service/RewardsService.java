package com.rar.service;

import com.rar.model.Rewards;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

public interface RewardsService {

    public Rewards Update(Long id, Rewards createreward);

    public Rewards updateAwardStatus(Long id, Rewards createreward);

    public Rewards discontinuing(Long id, Rewards createreward);

    Optional<Rewards> findById(Long id);

    Rewards save(Rewards rewards);

    List<Rewards> findAll();

    void deleteById(long id);

    List<Rewards> findByRolled(String email) throws Exception;

    public ResponseEntity rewardsSave(Rewards rewards);

    List<Rewards> latest(String email);

    List<Rewards> managerApprovalRewards(String email);
}
