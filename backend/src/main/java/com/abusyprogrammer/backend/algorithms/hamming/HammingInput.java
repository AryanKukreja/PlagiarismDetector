package com.abusyprogrammer.backend.algorithms.hamming;

/**
 * The HammingInput class is used to structure the body of incoming
 * requests to the HammingController.
 * 
 * @author Aryan Kukreja
 * @version 1.0.0
 * @since 2021-03-27
 */
public class HammingInput {

    // The 2 text strings to compare, along with an optional offset for the shorter string
    private String text1, text2;
    private int offset;

    /**
     * This is the default constructor for the HammingInput
     */
    public HammingInput() {
        this.text1 = "";
        this.text2 = "";
        this.offset = 0;
    }

    /**
     * This constructor is used for initializing the input with the 2
     * strings to compare, but without any offset.
     * 
     * @param text1 String #1 to compare
     * @param text2 String #2 to compare
     */
    public HammingInput(String text1, String text2) {
        this.text1 = text1;
        this.text2 = text2;
        this.offset = 0;
    }

    /**
     * This constructor is used for initializing the input with the 2
     * strings to compare and an offset.
     * 
     * @param text1 String #1 to compare
     * @param text2 String #2 to compare
     * @param offset Offset to use for shorter string
     */
    public HammingInput(String text1, String text2, int offset) {
        this.text1 = text1;
        this.text2 = text2;
        this.offset = offset;
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

    /**
     * The getter for the offset.
     * 
     * @return offset
     */
    public int getOffset() {
        return this.offset;
    }

}
