/*
 * Author     - Aryan Kukreja | Student #: 100651838
 * Date Made  - Tuesday, November 26, 2019 @ 12:10 AM
 * Course     - Design and Analysis of Algorithms | SOFE 3770
 *
 * Purpose:
 *      - Major Project | Plagiarism-Detection Algorithm
 *
 * About:
 *      -
 *
 * Input      - Original string and plagiarized file
 * Output     - Number of words plagiarized
 *
 * Extra Info - No main method: This program is non-executable
 */

// This is a part of the SimilarityChecker package
package SimilarityChecker;

// Import all the require packages
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Jaccard Class
 * - A container class for the core plagiarism
 */
@SuppressWarnings({"unused"})
public class Jaccard {
    // Declare a field to store a bag of the original sentence (rather than constantly sending it in)
    private String[] originalSentence;

    /**
     * Jaccard() Class Constructor
     * - A class constructor used to create a class
     *
     * @param original The original text to compare against
     */
    public Jaccard(String original) {
        this.originalSentence = original.toLowerCase().replaceAll("[^a-z0-9. ]", "").replaceAll("\\. ", ".").split("\\.");
    }

    /**
     * changeOriginal() Class method
     * - Used to update the original string field.
     *
     * @param newOriginal The new string to set as original
     */
    public void changeOriginal(String newOriginal) {
        this.originalSentence = newOriginal.toLowerCase().replaceAll("[^a-z0-9. ]", "").replaceAll("\\. ", ".").split("\\.");
    }

    /**
     * jaccardImplementation() static method:
     * - Holds the implementation of the Jaccard algorithm for comparing strings.
     *
     * @param plagiarized The plagiarized sentence
     * @return Return the number of words of copied that are plagiarized
     */
    public double jaccardImplementation(String plagiarized) {
        // Clean up the strings passed in as variables, and save them as a list
        // Split the plagiarized string into a bag of words
        // Split the original string into a bag of sentences.
        List<String> plagiarizedBag = Arrays.asList(plagiarized.toLowerCase().replaceAll("[^a-z0-9 ]", "").split(" "));

        // Declare tracker variables
        // Declare a list to store the copied and original sentence
        double max = 0, maximum = 0;
        List<String> copied, originalString;

        // For every sentence in te original sentences bag
        for (String sentence : this.originalSentence) {
            // Split that sentence into a bag of words
            // Make a copy of plagiarizedBag
            originalString = Arrays.asList(sentence.split(" "));
            copied = new ArrayList<>(plagiarizedBag);

            // Find the intersection of the original and the copied
            copied.retainAll(originalString);

            // Calculate the amount of plagiarism between the current original string and the plagiarized one
            double amountPlagiarized = (double) copied.size() / (originalString.size() + plagiarizedBag.size() - copied.size());

            // Save it if it is the max (plagiarism value and number of words in it)
            if (amountPlagiarized > max) {
                max = amountPlagiarized;
                maximum = copied.size();
            }
        }

        // Return the maximum number of words plagiarized
        return maximum;
    }
}
