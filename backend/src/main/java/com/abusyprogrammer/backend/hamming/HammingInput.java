package com.abusyprogrammer.backend.hamming;

public class HammingInput {

    private String text1, text2;
    private int offset;

    public HammingInput() {
        this.text1 = "";
        this.text2 = "";
        this.offset = 0;
    }

    public HammingInput(String text1, String text2) {
        this.text1 = text1;
        this.text2 = text2;
        this.offset = 0;
    }

    public HammingInput(String text1, String text2, int offset) {
        this.text1 = text1;
        this.text2 = text2;
        this.offset = offset;
    }

    public String getText1() {
        return this.text1;
    }

    public String getText2() {
        return this.text2;
    }

    public int getOffset() {
        return this.offset;
    }

}
