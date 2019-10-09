package com.rar.service.impl;



import com.rar.model.RewardsCriteria;
import com.rar.repository.RewardsCriteriaRepository;
import com.rar.service.RewardsCriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

/*    @Override
    public void deleteById(long id) {

        rewardsCriteriaRepository.deleteById(id);
    }

    @Override
    public Optional<RewardsCriteria> findById(Long rewardid) {
        return rewardsCriteriaRepository.findById(rewardid);
    }*/
}
