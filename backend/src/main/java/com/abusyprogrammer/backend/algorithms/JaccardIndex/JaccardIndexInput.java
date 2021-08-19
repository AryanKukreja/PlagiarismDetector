package com.abusyprogrammer.backend.algorithms.JaccardIndex;

/**
 * The JaccardIndexInput class is used to structure the body of incoming requests
 * to the JaccardIndexController.
 * 
 * @author ______________
 * @version ______________
 * @since ______________
 */
public class JaccardIndexInput {

	// The 2 string texts to be compared
	private String text1, text2;

	/**
	 * The constructor for this class, with the 2 strings to compare with.
	 * 
	 * @param text1 String #1 to compare
	 * @param text2 String #2 to compare
	 */
	public JaccardIndexInput(String text1, String text2) {
		this.text1 = text1;
		this.text2 = text2;
	}

	/**
	 * The getter for text1.
	 * 
	 * @return text1
	 */
	public String getText1() {
		return text1;
	}

	/**
	 * The getter for text2.
	 * 
	 * @return text2
	 */
	public String getText2() {
		return text2;
	}
}