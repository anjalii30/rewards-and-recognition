package com.rar.service.impl;



import com.rar.model.RewardsCriteria;
import com.rar.repository.RewardsCriteriasRepository;
import com.rar.service.RewardsCriteriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RewardsCriteriasServiceImpl implements RewardsCriteriasService {

    @Autowired
    RewardsCriteriasRepository rewardsCriteriasRepository;


    @Override
    public RewardsCriteria save(RewardsCriteria rewardsCriteria) {
        return rewardsCriteriasRepository.save(rewardsCriteria);
    }

    @Override
    public List<RewardsCriteria> findAll() {
        return (List<RewardsCriteria>) rewardsCriteriasRepository.findAll();
    }

/*    @Override
    public void deleteById(long id) {

        rewardsCriteriasRepository.deleteById(id);
    }

    @Override
    public Optional<RewardsCriteria> findById(Long rewardid) {
        return rewardsCriteriasRepository.findById(rewardid);
    }*/
}
