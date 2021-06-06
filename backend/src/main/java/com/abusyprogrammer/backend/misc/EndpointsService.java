package com.abusyprogrammer.backend.misc;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EndpointsService {
    ArrayList<JSONObject> endpoints;

    public EndpointsService() {
        this.endpoints = new ArrayList<JSONObject>();

        // New endpoints need to be specified here in this format
        HashMap<String, String> hammingInfo = new HashMap<String, String>() {{
            put("name", "Hamming Distance");
            put("path", "api/hamming/");
        }};

        this.endpoints.add(new JSONObject(hammingInfo));
    }
    
    @GetMapping(path = "api/misc/endpoints/")
    public String getEndpoints() {
        JSONObject res = new JSONObject();
        res.put("endpoints", this.endpoints);
        
        return res.toString();
    }

}
