package com.rar.service.impl;//package com.signon.service.impl;
//
//import com.signon.model.NominationCriterias;
//import com.signon.model.Nominations;
//import com.signon.repository.CriteriasRepository;
//import com.signon.repository.NominationCriteriasRepository;
//import com.signon.service.NominationCriteriasService;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class NominationCriteriasServiceImpl implements NominationCriteriasService {
//
//    NominationCriteriasRepository nominationCriteriasRepository;
//
//
//    @Override
//    public NominationCriterias save(NominationCriterias nominationCriterias) {
//        return nominationCriteriasRepository.save(nominationCriterias);
//    }
//
//    @Override
//    public List<NominationCriterias> findAll() {
//        return (List<NominationCriterias>) nominationCriteriasRepository.findAll();
//    }
//
//    @Override
//    public void deleteById(long id) {
//
//        nominationCriteriasRepository.deleteById(id);
//    }
//
//    @Override
//    public Optional<NominationCriterias> findById(Long id) {
//        return nominationCriteriasRepository.findById(id);
//    }
//}
