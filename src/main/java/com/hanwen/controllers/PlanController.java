package com.hanwen.controllers;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.hanwen.model.LinkedPlanServices;
import com.hanwen.model.Plan;
import com.hanwen.repositories.PlanRepository;
import com.hanwen.service.PlanService;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;


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
    public ResponseEntity update(@PathVariable("id") String id, @RequestBody String json, WebRequest request) {
        Plan plan = planRepository.findById(id);
        if(plan == null) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        Gson gson = new Gson();
        try {
            Plan data = gson.fromJson(json, Plan.class);

            if (request.checkNotModified(plan.getObjectId())) {
                // 2. shortcut exit - no further processing necessary
                //  it will also convert the response to an 304 Not Modified
                //  with an empty body
                return new ResponseEntity<>(plan, HttpStatus.NOT_MODIFIED);
            }

            planRepository.delete(id);
            planRepository.save(data);

            return ResponseEntity.ok()
            .eTag(data.getObjectId())
            .body(data);
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
    public ResponseEntity<Plan> findById(@PathVariable("id") String id, WebRequest request) {
        Plan plan = planRepository.findById(id);
        if(plan == null) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        if (request.checkNotModified(plan.getObjectId())) {
            // 2. shortcut exit - no further processing necessary
            //  it will also convert the response to an 304 Not Modified
            //  with an empty body
            return new ResponseEntity<>(plan, HttpStatus.NOT_MODIFIED);
        }

        return ResponseEntity.ok()
        .eTag(plan.getObjectId())
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
    public ResponseEntity patch(@PathVariable("id") String id, @RequestBody String json, WebRequest request) {
        Plan plan = planRepository.findById(id);
        if(plan == null) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        Gson gson = new Gson();
        try {
            Plan data = gson.fromJson(json, Plan.class);

            List<LinkedPlanServices> list = plan.getLinkedPlanServices();

            for(LinkedPlanServices lps : data.getLinkedPlanServices()) {
                list.add(lps);
            }

            if (request.checkNotModified(plan.getObjectId())) {
                // 2. shortcut exit - no further processing necessary
                //  it will also convert the response to an 304 Not Modified
                //  with an empty body
                return new ResponseEntity<>(plan, HttpStatus.NOT_MODIFIED);
            }

            planRepository.save(plan);

            return ResponseEntity.ok()
            .eTag(plan.getObjectId())
            .body(plan);
            // return new ResponseEntity<>(planRepository.findById(id), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>("Error when parsing JSON", HttpStatus.BAD_REQUEST);
        }
    }
}
