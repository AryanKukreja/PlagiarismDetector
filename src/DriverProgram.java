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

// Import the following packages
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * DriverProgram Class
 * - Used to get data from 2 entire files and save it as a String
 *
 * @version 1.0.0
 */
class DriverProgram {
    /**
     * driverProgram() Static Method
     * - The method that reads in 2 files and passes it to the wholeFileChecker as Strings.
     *
     * @param src  The origin file
     * @param plag The plagiarized file
     * @return The plagiarism value computed for the 2 files
     * @throws Exception For file-processing related errors
     */
    static double driverProgram(File src, File plag) throws Exception {
        // Declare a bufferReader object fr the source and plagiarized file, and read them in
        BufferedReader srcRead = new BufferedReader(new FileReader(src));
        BufferedReader plagRead = new BufferedReader(new FileReader(plag));

        // Declare a string builder to read the line from the file into that string
        // Also declare a temporary string used to read from the files
        StringBuilder source = new StringBuilder(), plagiarized = new StringBuilder();
        String temp;

        // Read every line from the source file and add it to the Stringbuilder
        while ((temp = srcRead.readLine()) != null) {
            source.append(temp);
        }
        // Do the same for the plagiarized file
        while ((temp = plagRead.readLine()) != null) {
            plagiarized.append(temp);
        }

        // Return the plagiarism level for the entire file system
        return WholeFileChecker.checkWholeFile(source.toString(), plagiarized.toString());
    }
}
