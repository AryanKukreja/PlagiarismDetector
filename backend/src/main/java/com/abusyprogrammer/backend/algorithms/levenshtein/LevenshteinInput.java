package com.abusyprogrammer.backend.algorithms.levenshtein;

public class LevenshteinInput {
	private String text1, text2;

	public LevenshteinInput() {
		this.text1 = "";
		this.text2 = "";
	}

	public LevenshteinInput(String text1, String text2) {
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