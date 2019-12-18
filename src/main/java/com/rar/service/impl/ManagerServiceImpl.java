package com.rar.service.impl;

import com.rar.model.Manager;
import com.rar.repository.ManagerRepository;
import com.rar.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public ResponseEntity<Manager> save(Manager manager) {
        return new ResponseEntity<>(managerRepository.save(manager),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Manager>> findAll() {
        return new ResponseEntity<>((List<Manager>) managerRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public void deleteById(long id) {

        managerRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<List<Map<String,String>>> getAllMembers(Long project_id) {
        return new ResponseEntity<>(managerRepository.getAllMembers(project_id),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Manager> findById(Long id) {
        return new ResponseEntity(managerRepository.findById(id),HttpStatus.OK);
    }

    @Override
    public void assignValues(long manager_id,long project_id){
        managerRepository.assignValues(manager_id,project_id);
    }

}
