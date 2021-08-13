package com.abusyprogrammer.backend.misc;

import java.util.ArrayList;
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
    ArrayList<JSONObject> endpoints;

    /**
     * EndpointsController() is used to create the endpoints on server startup
     */
    public EndpointsController() {
        this.endpoints = new ArrayList<JSONObject>();

        // New endpoints need to be specified here in this format and added to the list
        // of endpoints
        HashMap<String, String> hammingInfo = new HashMap<String, String>() {
            {
                put("name", "Hamming Distance");
                put("path", "api/hamming/");
            }
        };
        this.endpoints.add(new JSONObject(hammingInfo));
    }

    /**
     * getEndpoints() returns a list of available endpoints.
     * 
     * @return List of endpoints
     */
    @GetMapping(path = "api/misc/endpoints/")
    public String getEndpoints() {
        JSONObject res = new JSONObject();
        res.put("endpoints", this.endpoints);

        return res.toString();
    }

}
