package com.abusyprogrammer.backend.generic;

public class Input {

    private String text1, text2;

    public Input() {
        this.text1 = "";
        this.text2 = "";
    }

    public Input(String text1, String text2) {
        this.text1 = text1;
        this.text2 = text2;
    }

    public String getText1() {
        return text1;
    }

    public String getText2() {
        return text2;
    }

}
