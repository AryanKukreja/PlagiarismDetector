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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

class DriverProgram {
    static double driverProgram(File src, File plag) throws Exception {
        BufferedReader srcRead = new BufferedReader(new FileReader(src));
        BufferedReader plagRead = new BufferedReader(new FileReader(plag));

        StringBuilder source = new StringBuilder(), plagiarized = new StringBuilder();
        String temp;
        while ((temp = srcRead.readLine()) != null) {
            source.append(temp);
        }
        while ((temp = plagRead.readLine()) != null) {
            plagiarized.append(temp);
        }

        double result = WholeFileChecker.checkWholeFile(source.toString(), plagiarized.toString());
        System.out.println(result);
        return result;
    }
}
