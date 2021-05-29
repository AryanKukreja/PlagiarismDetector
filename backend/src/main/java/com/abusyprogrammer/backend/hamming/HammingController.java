package com.abusyprogrammer.backend.hamming;

import com.abusyprogrammer.backend.generic.Input;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/hamming/")
public class HammingController {
    
    @PostMapping(consumes = "application/json", produces = "application/json")
    public String compare(@RequestBody Input input) {
        Hamming service = new Hamming(input.getText1(), input.getText2());
    }

}