package com.abusyprogrammer.backend.misc;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * EndpointsController class returns a list of available algorithms to use from
 * the backend
 * 
 * @author Aryan Kukreja
 * @version 1.0.0
 * @since 2021-04-03
 */
@RestController
public class EndpointsController {
    HashMap<String, JSONObject> endpoints;

    /**
     * EndpointsController() is used to create the endpoints on server startup
     */
    public EndpointsController() {
        this.endpoints = new HashMap<>();

        this.endpoints.put("Hamming", new JSONObject(new HashMap<String, String>() {
            {
                put("name", "Hamming Distance");
                put("path", "api/hamming/");
            }
        }));
        this.endpoints.put("Levenshtein", new JSONObject(new HashMap<String, String>() {
            {
                put("name", "Levenshtein Distance");
                put("path", "api/levenshtein/");
            }
        }));
        this.endpoints.put("Jaccard", new JSONObject(new HashMap<String, String>() {
            {
                put("name", "Jaccard Distance");
                put("path", "api/jaccard-distance/");
            }
        }));
        this.endpoints.put("SorensenDice", new JSONObject(new HashMap<String, String>() {
            {
                put("name", "Sorensen-Dice Coefficient");
                put("path", "api/sorensen-dice-coefficient/");
            }
        }));
    }

    /**
     * getEndpoints() returns a list of available endpoints.
     * 
     * @return List of endpoints
     */
    @GetMapping(path = "api/misc/endpoints/")
    public String getEndpoints() {
        JSONObject result = new JSONObject();
        result.putAll(this.endpoints);

        return result.toJSONString();
    }

}
