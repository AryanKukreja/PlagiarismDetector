package com.abusyprogrammer.backend.algorithms.JaccardIndex;

// Imports
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JaccardIndexService class has the JaccardIndex algorithm implementations for
 * the controller to work with
 * 
 * @author ___________
 * @version ________
 * @since _________
 */
@JsonAutoDetect(fieldVisibility = Visibility.NON_PRIVATE)
public class JaccardIndexService {
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
	public JaccardIndexService(String text1, String text2) {
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

	public int getDifferences() {
		return this.differences;
	}

	/**
	 * Calculates the similarity score
	 * @return
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