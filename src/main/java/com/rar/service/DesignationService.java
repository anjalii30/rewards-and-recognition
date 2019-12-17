package com.rar.service;

import com.rar.entity.Designation;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface DesignationService {

    ResponseEntity<Designation> findById(Long id);

    Designation save(Designation designation);

    ResponseEntity<List<Designation>> findAll();

    void deleteById(long id);

}
