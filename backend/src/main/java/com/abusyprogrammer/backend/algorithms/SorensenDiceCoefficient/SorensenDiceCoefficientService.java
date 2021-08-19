package com.abusyprogrammer.backend.algorithms.SorensenDiceCoefficient;

import java.util.HashMap;
import java.util.HashSet;

// Imports
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * SorensenDiceCoefficientService class has the SorensenDiceCoefficient algorithm implementations for
 * the controller to work with
 * 
 * @author Aryan kukreja
 * @version 1.0.0
 * @since 2021-08-17
 */
@JsonAutoDetect(fieldVisibility = Visibility.NON_PRIVATE)
public class SorensenDiceCoefficientService {
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
	public SorensenDiceCoefficientService(String text1, String text2) {
		this.text1 = text1;
		this.text2 = text2;
		this.score = 0.0;
	}

	/**
	 * Generates a HashMap of bigrams based on the provided text
	 * 
	 * @param text Input text to convert to bigrams
	 * @return The HashMap of bigrams tracked (HashMap is used to track duplicates)
	 */
	public HashMap<String, Integer> getBigrams(String text) {
		HashMap<String, Integer> bigrams = new HashMap<String, Integer>();

		for (int i = 0; i < text.length() - 1; i++) {
			String bigram = "" + text.charAt(i) + text.charAt(i + 1);
			if (bigrams.containsKey(bigram)) {
				bigrams.put(bigram, bigrams.get(bigram) + 1);
			}
			else {
				bigrams.put(bigram, 1);
			}
		}

		return bigrams;
	}

	/**
	 * Returns a count of all the bigrams in the sentence, including duplicates.
	 * 
	 * @param textBigram The bigram to record
	 * @return The number of bigrams
	 */
	public int getDuplicateBigramCount(HashMap<String, Integer> textBigram) {
		int total = 0;
		for (String key : textBigram.keySet()) {
			total += textBigram.get(key);
		}

		return total;
	}

	/**
	 * The main computation function where the algorithm is implemented. It 
	 * 
	 * @return Status of function completion (success or failure)
	 * ['AA'], ['AA', 'AA', 'AA', 'AA'] => {'AA'}, {'AA'} => {'AA'} => 2 / 
	 */
	public int computation() {
		if (this.text1.equals("") || this.text2.equals("")) {
			return -1;
		}
		
		// Get the bigrams
		HashMap<String, Integer> text1Bigrams = this.getBigrams(this.text1);
		HashMap<String, Integer> text2Bigrams = this.getBigrams(this.text2);

		// Get the intersection of them
		HashSet<String> intersection = new HashSet<>();
		for (String key : text1Bigrams.keySet()) {
			if (text2Bigrams.containsKey(key)) {
				intersection.add(key);
			}
		}

		// Get the count of bigrams in both texts (including duplicates)
		int text1BigramCount = this.getDuplicateBigramCount(text1Bigrams);
		int text2BigramCount = this.getDuplicateBigramCount(text2Bigrams);

		// Get the similarity score
		this.score = (2 * intersection.size()) / (double) (text1BigramCount + text2BigramCount);

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