package com.abusyprogrammer.backend.algorithms.SorensenDiceCoefficient;

// Imports
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The SorensenDiceCoefficientController class accepts requests to the /api/hamming endpoint
 * and forwards processing to SorensenDiceCoefficientService
 * 
 * @author Aryan Kukreja
 * @version 1.0.0
 * @since 2021-08-18
 */
@RestController
@RequestMapping(path = "api/sorensen-dice-coefficient/")
public class SorensenDiceCoefficientController {

	/**
	 * Parses the incoming POST body, performs the comparison between the 2 passed
	 * strings, and returns the results
	 * 
	 * @param input The input body from the POST request from the user
	 * @return The results of the computation
	 * @throws JsonProcessingException Error in parsing the results into JSON format
	 */
	@PostMapping(consumes = "application/json", produces = "application/json")
	public String compare(@RequestBody SorensenDiceCoefficientInput input) throws JsonProcessingException {
		SorensenDiceCoefficientService service = new SorensenDiceCoefficientService(input.getText1(), input.getText2());

		int result = service.computation();
		return result == -1 ? "{\"error\": \"Cannot run processing with blank strings.\"}" : service.jsonify();
	}
}