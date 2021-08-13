package com.abusyprogrammer.backend.algorithms.hamming;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * HammingService class has the Hamming algorithm implementations for the
 * controller to work with
 * 
 * @author Aryan Kukreja
 * @version 1.0.0
 * @since 2021-03-28
 */
@JsonAutoDetect(fieldVisibility = Visibility.NON_PRIVATE)
public class HammingService {

    // Required properties for the Hamming algorithm computation
    String longer, shorter;
    double score;
    int matches;

    /**
     * This constructor is used for initializing the input with the 2 strings to
     * compare. The scores and matches are set to 0 by default
     * 
     * @param text1 String #1 to compare
     * @param text2 String #2 to compare
     */
    public HammingService(String text1, String text2) {
        if (text1.length() > text2.length()) {
            this.longer = text1;
            this.shorter = text2;
        } else {
            this.longer = text2;
            this.shorter = text1;
        }

        this.score = 0.0;
        this.matches = 0;
    }

    /**
     * This fullStringCheck() checks for similarity between the 2 strings without
     * any offset specified
     * 
     * @return Success state of algorithm execution
     */
    public int fullStringCheck() {
        // Check for a blank string
        if (this.longer == "" || this.shorter == "") {
            return -1;
        }

        int matches = 0;
        for (int iterator = 0; iterator < this.shorter.length(); iterator++) {
            if (this.longer.charAt(iterator) == this.shorter.charAt(iterator)) {
                matches++;
            }
        }

        this.score = (double) (matches) / (double) (this.longer.length());
        this.matches = matches;

        return 0;
    }

    /**
     * This fullStringCheck() checks for similarity between the 2 strings with a
     * specific offset
     * 
     * @param offset The offset to use with the algorithm
     * @return Success state of algorithm execution
     */
    public int fullStringCheck(int offset) {
        // Check for blank strings
        if (this.longer == "" || this.shorter == "") {
            return -1;
        }

        // Check for an invalid offset specified
        if (offset >= (this.longer.length() - this.shorter.length()))
            return -2;

        int matches = 0;
        for (int iterator = offset; iterator < this.shorter.length() + offset; iterator++) {
            if (this.longer.charAt(iterator) == this.shorter.charAt(iterator - offset)) {
                matches++;
            }
        }

        this.score = (double) (matches) / (double) (this.longer.length());
        this.matches = matches;

        return 0;
    }

    /**
     * Getter for the longer of 2 texts
     * 
     * @return Get the score of similarity of the 2 texts
     */
    public double getScore() {
        return score;
    }

    /**
     * Getter for the number of matches between the 2 texts
     * 
     * @return Get the number of matches between the 2 texts
     */
    public int getMatches() {
        return matches;
    }

    /**
     * jsonify() returns a JSON string of the results to respond to the request
     * 
     * @return A JSON string of the results of execution
     * @throws JsonProcessingException
     */
    public String jsonify() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }
}
