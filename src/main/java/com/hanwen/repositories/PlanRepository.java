package com.hanwen.repositories;

import java.util.Map;

import com.hanwen.model.Plan;

public interface PlanRepository {

    void save(Plan plan);

    Plan findById(String id);

    Map<String, Plan> findAll();

    // void update(Blog blog);

    void delete(String id);

}
