package com.abusyprogrammer.backend.algorithms.hamming;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonAutoDetect(fieldVisibility = Visibility.NON_PRIVATE)
public class HammingService {

    String longer, shorter;
    double score;
    int matches;

    public HammingService() {
        this.longer = "";
        this.shorter = "";
        this.score = 0.0;
        this.matches = 0;
    }

    public HammingService(String text1, String text2) {
        if (text1.length() > text2.length()) {
            this.longer = text1;
            this.shorter = text2;
        }
        else {
            this.longer = text2;
            this.shorter = text1;
        }

        this.score = 0.0;
        this.matches = 0;
    }

    public int fullStringCheck() {
        if (this.longer == null || this.shorter == null) {
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

    public int fullStringCheck(int offset) {
        if (this.longer == null || this.shorter == null) {
            return -1;
        }

        if (offset > this.longer.length() - this.shorter.length())
            return -2;

        int matches = 0;
        for (int iterator = offset; iterator < this.shorter.length() + offset; iterator++) {
            if (this.longer.charAt(iterator) == this.shorter.charAt(iterator)) {
                matches++;
            }
        }

        this.score = (double) (matches) / (double) (this.longer.length());
        this.matches = matches;

        return 0;
    }

    public String jsonify() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

}
