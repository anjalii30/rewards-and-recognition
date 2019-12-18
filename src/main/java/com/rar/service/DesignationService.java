package com.rar.service;

import com.rar.model.Designation;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DesignationService {

    ResponseEntity<Designation> findById(Long id);

    Designation save(Designation designation);

    ResponseEntity<List<Designation>> findAll();

    void deleteById(long id);

}
