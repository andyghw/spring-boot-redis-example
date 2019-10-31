package com.hanwen.controllers;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.hanwen.model.LinkedPlanServices;
import com.hanwen.model.Plan;
import com.hanwen.repositories.PlanRepository;
import com.hanwen.service.PlanService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
// @RequestMapping("/hanwen")
public class PlanController {
    private PlanService planService;
    private PlanRepository planRepository;

    public PlanController(PlanRepository planRepository, PlanService planService) {
        this.planRepository = planRepository;
        this.planService = planService;
    }

    @RequestMapping(value = "/plan", method = RequestMethod.POST)
    public ResponseEntity add(@RequestBody String json) {
        Plan plan = planService.parseToPlan(json);
        if(plan == null) {
            return new ResponseEntity<>("Request body doesn't match with JSON schema.", HttpStatus.BAD_REQUEST);
        }
        if(planRepository.findById(plan.getObjectId()) != null) {
            return new ResponseEntity<>("Plan with id " + plan.getObjectId() + " has already existed.", HttpStatus.BAD_REQUEST);
        }
        planRepository.save(plan);
        return new ResponseEntity<Plan>(planRepository.findById(plan.getObjectId()), HttpStatus.OK);
    }

    @RequestMapping(value = "/plan/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable("id") String id, @RequestBody String json) {
        Plan plan = planRepository.findById(id);
        if(plan == null) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        Gson gson = new Gson();
        try {
            Plan data = gson.fromJson(json, Plan.class);

            planRepository.delete(id);
            planRepository.save(data);

            return new ResponseEntity<>(planRepository.findById(id), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>("Error when parsing JSON", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/plan", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Plan>> all() {
        Map<String, Plan> map = planRepository.findAll();
        if(map.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok()
        .eTag(String.valueOf(map))
        .body(map);
    }

    @RequestMapping(value = "/plan/{id}", method = RequestMethod.GET)
    public ResponseEntity<Plan> findById(@PathVariable("id") String id) {
        Plan plan = planRepository.findById(id);
        if(plan == null) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok()
        .eTag(String.valueOf(plan))
        .body(plan);
    }

    @RequestMapping(value = "/plan/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") String id) {
        Plan plan = planRepository.findById(id);
        if(plan == null) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        planRepository.delete(id);
        return new ResponseEntity<>(planRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/plan/{id}", method = RequestMethod.PATCH)
    public ResponseEntity patch(@PathVariable("id") String id, @RequestBody String json) {
        Plan plan = planRepository.findById(id);
        if(plan == null) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        Gson gson = new Gson();
        try {
            LinkedPlanServices[] data = gson.fromJson(json, LinkedPlanServices[].class);

            List<LinkedPlanServices> list = plan.getLinkedPlanServices();

            for(LinkedPlanServices lps : data) {
                list.add(lps);
            }

            planRepository.save(plan);

            return new ResponseEntity<>(planRepository.findById(id), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>("Error when parsing JSON", HttpStatus.BAD_REQUEST);
        }
    }

    // @RequestMapping(value = "/registry", method = RequestMethod.GET)
    // public RedirectView redirect() {
    //     return new RedirectView("/plan");
    // }
}
