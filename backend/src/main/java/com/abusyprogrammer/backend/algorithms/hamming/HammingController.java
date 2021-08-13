package com.abusyprogrammer.backend.algorithms.hamming;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The HammingController class accepts requests to the api/hamming/ endpoint and
 * forwards processing to HammingService
 * 
 * @author Aryan Kukreja
 * @version 1.0.0
 * @since 2021-03-28
 */
@RestController
@RequestMapping(path = "api/hamming/")
public class HammingController {

    /**
     * The compare() function is linked with the /api/hamming endpoint and processes
     * all string similarity checking requests.
     * 
     * @param input The body of the POST request formatted as a HammingInput object
     * @return JSON string of results
     * @throws JsonProcessingException
     */
    @PostMapping(consumes = "application/json", produces = "application/json")
    public String compare(@RequestBody HammingInput input) throws JsonProcessingException {
        HammingService service = new HammingService(input.getText1(), input.getText2());

        int result;
        if (input.getOffset() == 0) {
            result = service.fullStringCheck();
        } else {
            result = service.fullStringCheck(input.getOffset());
        }

        if (result == -1) {
            return "{\"error\": \"Cannot run processing with blank strings.\"}";
        } else if (result == -2) {
            return "{\"error\": \"The offset cannot be larger than the texts passed in.\"}";
        }

        return service.jsonify();
    }

}