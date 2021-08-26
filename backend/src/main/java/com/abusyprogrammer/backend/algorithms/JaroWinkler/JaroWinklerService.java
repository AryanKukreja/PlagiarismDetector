package com.abusyprogrammer.backend.algorithms.JaroWinkler;

// Imports
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JaroWinklerService class has the JaroWinkler algorithm implementations for
 * the controller to work with
 * 
 * @author __________
 * @version 1.0.0
 * @since 2021-08-19
 */
@JsonAutoDetect(fieldVisibility = Visibility.NON_PRIVATE)
public class JaroWinklerService {
	// The 2 text strings to compare
	String text1, text2;
	double score;
	int differences;

	/**
	 * This constructor is used for initializing the input with the 2 strings to
	 * 
	 * @param text1 String #1 to compare
	 * @param text2 String #2 to compare
	 */
	public JaroWinklerService(String text1, String text2) {
		this.text1 = text1;
		this.text2 = text2;
		this.differences = 0;
		this.score = 0.0;
	}

	/**
	 * The main computation function where the algorithm is implemented.
	 * 
	 * @return Status of function completion (success or failure)
	 */
	public int computation() {
		if (this.text1.equals("") || this.text2.equals("")) {
			return -1;
		}
		
		// TODO Algorithm to implement

		return 0;
	}

	/**
	 * Returns a jsonified version of the object, to be returned to the user.
	 * 
	 * @return The data in JSON format
	 * @throws JsonProcessingException If the JSON could not be formed
	 */
	public String jsonify() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(this);
	}

	/**
	 * Return the difference count between the 2 strings
	 * 
	 * @return differences attribute
	 */
	public int getDifferences() {
		return this.differences;
	}

	/**
	 * Calculates the similarity score

	 * @return score
	 */
	public double getScore() {
		return this.score;
	}

	    /**
     * The getter for text1.
     * 
     * @return text1
     */
    public String getText1() {
			return this.text1;
	}

	/**
	 * The getter for text2.
	 * 
	 * @return text2
	 */
	public String getText2() {
			return this.text2;
	}
}