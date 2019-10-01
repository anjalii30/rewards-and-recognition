package com.rar.service.impl;



import com.rar.model.RewardsCriterias;
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
    public RewardsCriterias save(RewardsCriterias rewardsCriterias) {
        return rewardsCriteriasRepository.save(rewardsCriterias);
    }

    @Override
    public List<RewardsCriterias> findAll() {
        return (List<RewardsCriterias>) rewardsCriteriasRepository.findAll();
    }

/*    @Override
    public void deleteById(long id) {

        rewardsCriteriasRepository.deleteById(id);
    }

    @Override
    public Optional<RewardsCriterias> findById(Long rewardid) {
        return rewardsCriteriasRepository.findById(rewardid);
    }*/
}
