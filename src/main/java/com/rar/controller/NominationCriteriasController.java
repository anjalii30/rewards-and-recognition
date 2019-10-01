package com.rar.controller;//package com.signon.controller;
//
//
//import com.signon.model.NominationCriterias;
//import com.signon.service.NominationCriteriasService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@CrossOrigin
//@Transactional
//public class NominationCriteriasController {
//
//    @Autowired
//    private NominationCriteriasService nominationCriteriasService;
//
//    @PostMapping("/saveNominationCriterias")
//    public NominationCriterias save(@RequestHeader(value = "Authorization") String token, @RequestBody NominationCriterias nominationCriterias){
//        return nominationCriteriasService.save(nominationCriterias);
//    }
//
//    @GetMapping("/listNominationCriterias")
//    public List<NominationCriterias> list(@RequestHeader(value = "Authorization") String token){
//        return nominationCriteriasService.findAll();
//    }
//
//    @DeleteMapping("/deleteNominationCriterias/{id}")
//    public String delete(@RequestHeader(value = "Authorization") String token, @PathVariable long id){
//        nominationCriteriasService.deleteById(id);
//        return "Deleted Successfully";
//    }
//
//    @GetMapping("/listNominationCriterias/{id}")
//    public Optional<NominationCriterias> getById(@RequestHeader(value = "Authorization") String token, @PathVariable Long id){
//
//        return nominationCriteriasService.findById(id);
//    }
//}
