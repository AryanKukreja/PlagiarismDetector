/*
 * Author     - Aryan Kukreja | Student #: 100651838
 * Date Made  - Sunday, November 24, 2019 @ 10:47 AM
 * Course     - Design and Analysis of Algorithms | SOFE 3770
 *
 * Purpose:
 *      - Project | Plagiarism Detection
 *
 * About:
 *      - This is a Rabin-Karp implementation of string comparison.
 *
 * Input      - A source string and a plagiarized string
 * Output     - Starting points of common elements in the string
 *
 * Extra Info - This algorithm is not used in the main program; however, it may be of use for future developments on this project
 */
package SimilarityChecker;

import java.util.ArrayList;

public class RabinKarpAlgorithm {
    public ArrayList<Integer> rabinKarpComparison(String source, String pattern) {
        int patLen = pattern.length(), srcLen = source.length();
        ArrayList<Integer> finalList = new ArrayList<>();

        source = source.toLowerCase().replaceAll("[^a-z0-9]", "");
        pattern = pattern.toLowerCase().replaceAll("[^a-z0-9]", "");

        long patHash = 0, tempHash = 0;
        for (int index = 0; index < patLen; index++) {
            patHash += (pattern.charAt(index) - 96) * Math.pow(26, (patLen - index - 1));
            tempHash += (source.charAt(index) - 96) * Math.pow(26, (patLen - index - 1));
        }

        for (int counter = 0; counter < srcLen - patLen; counter++) {
            if (tempHash == patHash) {
                finalList.add(counter);
            }
            tempHash -= (source.charAt(counter) - 96) * Math.pow(26, patLen - 1);
            tempHash *= 26;
            tempHash += source.charAt(counter + patLen) - 96;
        }

        if (tempHash == patHash) {
            finalList.add(srcLen - patLen);
        }

        return finalList;
    }
}
