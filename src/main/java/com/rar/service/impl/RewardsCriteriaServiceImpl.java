package com.rar.service.impl;

import com.rar.model.RewardsCriteria;
import com.rar.repository.RewardsCriteriaRepository;
import com.rar.service.RewardsCriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RewardsCriteriaServiceImpl implements RewardsCriteriaService {

    @Autowired
    private RewardsCriteriaRepository rewardsCriteriaRepository;

    @Override
    public RewardsCriteria save(RewardsCriteria rewardsCriteria) {
        return rewardsCriteriaRepository.save(rewardsCriteria);
    }

    @Override
    public List<RewardsCriteria> findAll() {
        return (List<RewardsCriteria>) rewardsCriteriaRepository.findAll();
    }

    public void deleteById(Long rid, Long cid) {

        rewardsCriteriaRepository.deleteById(rid,cid);
    }

    @Override
    public Set<RewardsCriteria> findById(Long rid, Long cid) {
        return rewardsCriteriaRepository.findById(rid,cid);
    }
}
