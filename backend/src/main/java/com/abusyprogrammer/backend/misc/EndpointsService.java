package com.abusyprogrammer.backend.misc;

import java.util.HashMap;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EndpointsService {
    HashMap<String, String> endpoints;

    public EndpointsService() {
        this.endpoints = new HashMap<>();

        // New endpoints need to be specified here in this format
        this.endpoints.put("Hamming Distance", "api/hamming/");
    }
    
    @GetMapping(path = "api/misc/endpoints/")
    public String getEndpoints() {
        JSONObject res = new JSONObject(this.endpoints);
        return res.toString(2);
    }

}
