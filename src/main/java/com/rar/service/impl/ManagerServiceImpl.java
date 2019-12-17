package com.rar.service.impl;

import com.rar.entity.Manager;
import com.rar.repository.ManagerRepository;
import com.rar.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public Manager save(Manager manager) {
        return managerRepository.save(manager);
    }

    @Override
    public List<Manager> findAll() {
        return (List<Manager>) managerRepository.findAll();
    }

    @Override
    public void deleteById(long id) {

        managerRepository.deleteById(id);
    }

    //not used
   /* @Override
    public List getEmployees(Long manager_id) {
        return managerRepository.getEmployees(manager_id);
    }*/

    @Override
    public List<Map<String,String>> getAllMembers(Long project_id) {
        return managerRepository.getAllMembers(project_id);
    }

    @Override
    public Optional<Manager> findById(Long id) {
        return managerRepository.findById(id);
    }

    @Override
    public void assignValues(long manager_id,long project_id){
        managerRepository.assignValues(manager_id,project_id);
    }

}
