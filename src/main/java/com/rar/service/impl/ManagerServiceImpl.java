package com.rar.service.impl;

import com.rar.model.Manager;
import com.rar.model.UserInfo;
import com.rar.repository.ManagerRepository;
import com.rar.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Override
    public List getEmployees(Long manager_id) {
        return managerRepository.getEmployees(manager_id);
    }

    @Override
    public Optional<Manager> findById(Long id) {
        return managerRepository.findById(id);
    }
}
