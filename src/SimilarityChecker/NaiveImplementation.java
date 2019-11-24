/*
 * Author     - Aryan Kukreja | Student #: 100651838
 * Date Made  - Saturday, November 23, 2019 @ 11:10 AM
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

public class NaiveImplementation {
    public ArrayList<Integer> naiveComparison(String source, String pattern) {
        ArrayList<Integer> startPoints = new ArrayList<>();
        int iterate = source.length() - pattern.length() + 1;
        int patLen = pattern.length();

        for (int index = 0; index < iterate; index++) {
            boolean valid = true;
            for (int count = index, patTrack = 0; count < index + patLen; count++, patTrack++) {
                if (source.charAt(count) != pattern.charAt(patTrack)) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                startPoints.add(index);
            }
        }

        return startPoints;
    }
}
