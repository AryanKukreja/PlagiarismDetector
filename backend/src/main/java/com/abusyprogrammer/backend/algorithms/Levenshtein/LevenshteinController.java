package com.abusyprogrammer.backend.algorithms.Levenshtein;

// Imports
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The LevenshteinController class accepts requests to the /api/hamming endpoint
 * and forwards processing to LevenshteinService
 * 
 * @author Aryan Kukreja
 * @version 1.0.0
 * @since 2021-08-13
 */
@RestController
@RequestMapping(path = "api/levenshtein/")
public class LevenshteinController {

	/**
	 * Parses the incoming POST body, performs the comparison between the 2 passed
	 * strings, and returns the results
	 * 
	 * @param input The input body from the POST request from the user
	 * @return The results of the computation
	 * @throws JsonProcessingException Error in parsing the results into JSON format
	 */
	@PostMapping(consumes = "application/json", produces = "application/json")
	public String compare(@RequestBody LevenshteinInput input) throws JsonProcessingException {
		LevenshteinService service = new LevenshteinService(input.getText1(), input.getText2());

		int result = service.computation();
		return result == -1 ? "{\"error\": \"Cannot run processing with blank strings.\"}" : service.jsonify();
	}
}