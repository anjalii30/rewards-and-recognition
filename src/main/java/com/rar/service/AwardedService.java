package com.rar.service;

import com.rar.model.Awarded;

import java.util.List;

public interface AwardedService {


    Awarded Update(Long id, Awarded awarded);

    Awarded save(Awarded awarded);

    Object findAllAwarded();
}