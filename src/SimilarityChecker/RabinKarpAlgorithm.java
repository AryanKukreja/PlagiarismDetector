/*
 * Author     - Aryan Kukreja | Student #: 100651838
 * Date Made  - Sunday, November 24, 2019 @ 10:47 AM
 * Course     - Design and Analysis of Algorithms | SOFE 3770
 *
 * Purpose:
 *      - Assignment # | Assignment Description
 *
 * About:
 *      -
 *
 * Input      - {List of Inputs}
 * Output     - {List of Outputs}
 *
 * Extra Info - {}
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
