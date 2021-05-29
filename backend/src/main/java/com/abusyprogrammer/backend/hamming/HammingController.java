package com.abusyprogrammer.backend.hamming;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/hamming/")
public class HammingController {
    
    @PostMapping(consumes = "application/json", produces = "application/json")
    public String compare(@RequestBody HammingInput input) throws JsonProcessingException {
        HammingService service = new HammingService(input.getText1(), input.getText2());

        if (input.getOffset() == 0) {
            service.fullStringCheck();
        }
        else {
            service.fullStringCheck(input.getOffset());
        }

        return service.jsonify();
    }

}