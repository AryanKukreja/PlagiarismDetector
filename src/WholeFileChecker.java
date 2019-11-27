/*
 * Author     - Aryan Kukreja | Student #: 100651838
 * Date Made  - Tuesday, November 26, 2019 @ 3:07 PM
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

import SimilarityChecker.Jaccard;

class WholeFileChecker {
    static double checkWholeFile(String origin, String copied) {
        String[] copiedArray = copied.split("\\. ");

        int totalPlagiarized = 0;
        int totalWords = copied.split("\\s+").length;
        System.out.println(totalWords);
        for (String s : copiedArray) {
            totalPlagiarized += Jaccard.jaccardImplementation(origin, s);
        }
        return (double) totalPlagiarized / totalWords;
    }

//    public static void main(String[] args) {
//        WholeFileChecker.checkWholeFile("In object-oriented programming. inheritance is a way to form new classes using classes that have already been defined. The inheritance concept was invented in 1967 for Simula.", "Inheritance in object oriented programming. is a way to form new classes. using classes that have already been defined.");
//    }
}
