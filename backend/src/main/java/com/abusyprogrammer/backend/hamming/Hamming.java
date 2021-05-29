package com.abusyprogrammer.backend.hamming;

public class Hamming {

    String longer, shorter;
    double score;

    public Hamming() {
        this.longer = "";
        this.shorter = "";
        this.score = 0.0;
    }

    public Hamming(String text1, String text2) {
        if (text1.length() > text2.length()) {
            this.longer = text1;
            this.shorter = text2;
        }
        else {
            this.longer = text2;
            this.shorter = text1;
        }
        this.score = 0.0;
    }

    public int calculateScore() {
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

        return 0;
    }

    public int calculateScore(int offset) {
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

        return 0;
    }

}
