/*
 * Author     - Aryan Kukreja | Student #: 100651838
 * Date Made  - Tuesday, November 26, 2019 @ 12:10 AM
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
import java.util.Arrays;
import java.util.List;

public class Jaccard {
    public static double jaccardImplementation(String wiki, String cheat) {
        // Cheat is just one fucking sentence
        // senlist is the entire damn original file
        List<String> cheatB = Arrays.asList(cheat.toLowerCase().replaceAll("[^a-z0-9 ]", "").split(" "));
        String[] senList = wiki.toLowerCase().replaceAll("[^a-z0-9. ]", "").replaceAll("\\. ", ".").split("\\.");

        int cheatSize = cheatB.size();
        double max = 0, maximum = 0;

        List<String> copied, sentB;
        for (String s : senList) {
            sentB = Arrays.asList(s.split(" "));
            copied = new ArrayList<>(cheatB);

            copied.retainAll(sentB);

            double amountPlagiarized = (double) copied.size() / (sentB.size() + cheatB.size() - copied.size());
            if (amountPlagiarized > max) {
                max = amountPlagiarized;
                maximum = copied.size();
            }
        }

        return maximum;
    }
}
