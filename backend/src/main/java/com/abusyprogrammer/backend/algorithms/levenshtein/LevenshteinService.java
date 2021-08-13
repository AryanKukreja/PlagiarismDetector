package com.abusyprogrammer.backend.algorithms.levenshtein;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonAutoDetect(fieldVisibility = Visibility.NON_PRIVATE)
public class LevenshteinService {
	// The 2 text strings to compare
	String text1, text2;
	double score;
	int differences;

	public LevenshteinService(String text1, String text2) {
		this.text1 = text1;
		this.text2 = text2;
		this.differences = 0;
		this.score = 0.0;
	}

	private int getMin(int[] array) {
		int min = 0;
		for (int i = 1; i < array.length; i++) {
			if (array[i] < array[min]) {
				min = i;
			}
		}

		return array[min];
	}

	public int recursiveComputation() {
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

		return distance[this.text1.length()][this.text2.length()];
	}

	public int computation(int text1End, int text2End) {
		if (this.text1.equals("") || this.text2.equals("")) {
			return -1;
		}

		this.differences = this.recursiveComputation();

		return 0;
	}

	public String jsonify() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(this);
	}
}