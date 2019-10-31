package com.hanwen.repositories;

import java.util.Map;

import com.hanwen.model.Plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PlanRepositoryImpl implements PlanRepository {

    private RedisTemplate<String, Plan> redisTemplate;

    private HashOperations hashOperations;

    public PlanRepositoryImpl(RedisTemplate<String, Plan> redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(Plan plan) {
        hashOperations.put("PLAN", plan.getObjectId(), plan);
    }

    @Override
    public Plan findById(String id) {
        return (Plan)hashOperations.get("PLAN", id);
    }

    // @Override
    // public void update(Blog blog) {
    //     save(blog);
    // }

    @Override
    public Map<String, Plan> findAll() {
        return hashOperations.entries("PLAN");
    }

    @Override
    public void delete(String id) {
        hashOperations.delete("PLAN", id);
    }
}
