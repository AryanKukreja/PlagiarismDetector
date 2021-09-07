package com.abusyprogrammer.backend.algorithms.Jaro;

// Imports
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JaroService class has the Jaro algorithm implementations for the controller
 * to work with
 * 
 * @author Aryan Kukreja
 * @version 1.0.0
 * @since 2021-08-19
 */
@JsonAutoDetect(fieldVisibility = Visibility.NON_PRIVATE)
public class JaroService {
	// The 2 text strings to compare
	String text1, text2;
	double score;
	int matches;

	/**
	 * This constructor is used for initializing the input with the 2 strings to
	 * 
	 * @param text1 String #1 to compare
	 * @param text2 String #2 to compare
	 */
	public JaroService(String text1, String text2) {
		this.text1 = text1;
		this.text2 = text2;
		this.matches = 0;
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

		this.matches = 0;
		double maxDist = Math.floor(Math.max(this.text1.length(), this.text2.length()) / 2) - 1;
		int[] hash1 = new int[this.text1.length()], hash2 = new int[this.text2.length()];

		for (int i = 0; i < this.text1.length(); i++) {
			for (int j = (int) Math.max(0, i - maxDist); j < (int) Math.min(this.text2.length(), i + maxDist + 1); j++) {
				if (this.text1.charAt(i) == this.text2.charAt(j) && hash2[j] == 0) {
					hash1[i] = 1;
					hash2[j] = 1;
					this.matches++;
					break;
				}
			}
		}

		if (matches == 0) {
			this.score = 0.0;
		}

		int transpositions = 0, points = 0;
		for (int i = 0; i < this.text1.length(); i++) {
			if (hash1[i] == 0) {
				while (hash2[points] == 0) {
					points++;
				}

				if (this.text1.charAt(i) != this.text2.charAt(points)) {
					points++;
					transpositions++;
				}
			}
		}

		this.score = ((double) this.matches / this.text1.length() + (double) this.matches / this.text2.length()
				+ (this.matches - transpositions + 1)) / 3.0 * this.matches;

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
	 * Return the matches count between the 2 strings
	 * 
	 * @return matches attribute
	 */
	public int getMatches() {
		return this.matches;
	}

	/**
	 * Calculates the similarity score
	 * 
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