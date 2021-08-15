package com.abusyprogrammer.backend.algorithms.levenshtein;

// Imports
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * LevenshteinService class has the Levenshtein algorithm implementations for
 * the controller to work with
 * 
 * @author Aryan Kukreja
 * @version 1.0.0
 * @since 2021-08-13
 */
@JsonAutoDetect(fieldVisibility = Visibility.NON_PRIVATE)
public class LevenshteinService {
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
	public LevenshteinService(String text1, String text2) {
		this.text1 = text1;
		this.text2 = text2;
		this.differences = 0;
		this.score = 0.0;
	}

	/**
	 * Gets the minimum value in an array
	 * 
	 * @param array The array to search
	 * @return The minimum value
	 */
	private int getMin(int[] array) {
		int min = 0;
		for (int i = 1; i < array.length; i++) {
			if (array[i] < array[min]) {
				min = i;
			}
		}

		return array[min];
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
		
		int[][] distance = new int[this.text1.length() + 1][this.text2.length() + 1];
		for (int i = 0; i <= this.text1.length(); i++) {
			distance[i][0] = i;
		}

		for (int j = 0; j <= this.text2.length(); j++) {
			distance[0][j] = j;
		}

		int[] operations = new int[3];
		for (int i = 1; i <= this.text1.length(); i++) {
			for (int j = 1; j <= this.text2.length(); j++) {
				if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
					distance[i][j] = distance[i - 1][j - 1];
				} else {
					operations[0] = distance[i - 1][j];
					operations[1] = distance[i][j - 1];
					operations[2] = distance[i - 1][j - 1];

					distance[i][j] = 1 + this.getMin(operations);
				}
			}
		}

		this.differences = distance[this.text1.length()][this.text2.length()];
		this.score = (double) this.differences / (double) Math.max(this.text1.length(), this.text2.length());

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