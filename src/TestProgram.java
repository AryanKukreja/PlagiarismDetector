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

import SimilarityChecker.*;

import java.util.ArrayList;

public class TestProgram {
    public static void main(String[] args) {
        String src = "abcdefcbghigjdabeft", pattern = "ab";

        NaiveImplementation naive = new NaiveImplementation();
        System.out.println(naive.naiveComparison(src, pattern));

        RabinKarpAlgorithm rabinKarpTest = new RabinKarpAlgorithm();
        System.out.println(rabinKarpTest.rabinKarpImplementation(src, pattern));
    }
}
