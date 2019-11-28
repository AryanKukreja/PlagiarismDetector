/*
 * Author     - Aryan Kukreja | Student #: 100651838
 * Date Made  - Tuesday, November 26, 2019 @ 3:07 PM
 * Course     - Design and Analysis of Algorithms | SOFE 3770
 *
 * Purpose:
 *      - Major Project | Plagiarism-Detection Algorithm
 *
 * About:
 *      - This program handles 2 entire files: an original file, and a plagiarized (possibly) one. It gets the plagiarism
 *        amount for the entire file.
 *
 * Input      - Location of original and plagiarized file
 * Output     - Plagiarism amount
 *
 * Extra Info - No main method: this file is non-executable
 */

// Import the Jaccard similarity checker method
import SimilarityChecker.Jaccard;

/**
 * WholeFileChecker Class
 * - Used to check similarities between whole files
 *
 * @version 1.0.0
 */
class WholeFileChecker {
    /**
     * checkWholeFile() method:
     * - Used to check plagiarism between 2 entire files.
     *
     * @param origin The original file
     * @param copied The file to check against the original
     * @return The plagiarism value
     */
    static double checkWholeFile(String origin, String copied) {
        // Split the string
        String[] copiedArray = copied.split("\\. ");

        // Declare a variable to track the total number of variables.
        // Get the number of words in the copied string.
        int totalPlagiarized = 0;
        int totalWords = copied.split("\\s+").length;

        // Declare an instance of the Jaccard algorithm
        Jaccard useJaccard = new Jaccard(origin);

        // For every string in the list of copied sentences
        for (String sentence : copiedArray) {
            // Sum up its plagiarized count.
            totalPlagiarized += useJaccard.jaccardImplementation(sentence);
        }

        // Return their percentage of plagiarism.
        return (double) totalPlagiarized / totalWords;
    }
}
