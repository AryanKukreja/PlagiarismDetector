package com.abusyprogrammer.backend.algorithms.levenshtein;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/levenshtein/")
public class LevenshteinController {

	@PostMapping(consumes = "application/json", produces = "application/json")
	public String compare(@RequestBody LevenshteinInput input) throws JsonProcessingException {
		LevenshteinService service = new LevenshteinService(input.getText1(), input.getText2());

		int result = service.computation(0, 0);
		return result == -1 ? "{\"error\": \"Cannot run processing with blank strings.\"}" : service.jsonify();
	}
}