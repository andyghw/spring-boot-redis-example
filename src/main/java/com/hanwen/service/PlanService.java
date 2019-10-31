package com.hanwen.service;

import java.io.FileReader;
import java.io.InputStream;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hanwen.model.Plan;

import org.springframework.stereotype.Service;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;


@Service
public class PlanService {
    public Plan parseToPlan(String json) {
        Gson gson = new Gson();
        try {
            // JsonParser parser = new JsonParser();
            InputStream inputStream = getClass().getResourceAsStream("use_case.json");
            // String jsonString = parser.parse(new FileReader("use_case.json")).toString();
            JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
            Schema schema = SchemaLoader.load(rawSchema);

            schema.validate(new JSONObject(json));

            Plan plan = gson.fromJson(json, Plan.class);
            return plan;
        } catch(Exception e) {
            System.out.println("Error happened when deserialize JSON.");
            System.out.println(e);
        }
        return null;
    }
}