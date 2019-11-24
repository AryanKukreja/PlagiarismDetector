/*
 * Author     - Aryan Kukreja | Student #: 100651838
 * Date Made  - Saturday, November 23, 2019 @ 11:08 AM
 * Course     - Design and Analysis of Algorithms | SOFE 3770
 *
 * Purpose:
 *      - Major Project | Plagiarism Detector (Similarity Checker)
 *
 * About:
 *      - This is the driver code for the similarity checker program
 *
 * Input      - 2 text files
 * Output     - Similarity Report
 *
 * Extra Info - This is only the tester program
 */

import SimilarityChecker.NaiveImplementation;

import java.util.ArrayList;

public class TestProgram {
    public static void main(String[] args) {
        NaiveImplementation work = new NaiveImplementation();
        String src = "abcdefcbghigjdabeft", pattern = "ab";
        ArrayList<Integer> results = work.naiveComparison(src, pattern);

        for (int element : results) {
            System.out.println(src.substring(element, element + pattern.length()));
        }
    }
}
