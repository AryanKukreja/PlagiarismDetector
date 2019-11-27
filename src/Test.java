/*
 * Author     - Aryan Kukreja | Student #: 100651838
 * Date Made  - Tuesday, November 26, 2019 @ 8:07 PM
 * Course     - Design and Analysis of Algorithms | SOFE 3770
 *
 * Purpose:
 *      - Major Project | Plagiarism-Detection Algorithm
 *
 * About:
 *      - This program is a tester class for the plagiarism-checker program. It checks 100 sample files and writes the
 *        output to a CSV file.
 *
 * Input      - Read all files from a project
 * Output     - Write plagiarism scores for all programs to the CSV file
 *
 * Extra Info - This class has a main() method.
 */

// Import the packages for reading and writing files to.
import java.io.File;
import java.io.PrintWriter;

/**
 * Test Class
 * - Used to test the plagiarism program
 *
 * @version 1.5.0
 */
public class Test {
    /**
     * main() Implementation Method:
     * - Used to implement the method
     *
     * @param args Command-Line arguments from the user
     * @throws Exception Exception during read/writing operations from the program
     */
    public static void main(String[] args) throws Exception {
        // Declare variables to store the location to the original and duplicate files.
        File originals, duplicates;

        // If the user specifies the folder location then use that to create the File objects
        // Else use the default project folder.
        if (args.length != 2) {
            originals = new File("src/Evaluation/Original");
            duplicates = new File("src/Evaluation/Plagiarized");
        } else {
            originals = new File(args[0]);
            duplicates = new File(args[1]);
        }

        // Declare n array to store all the files in the folders
        File[] originalList = originals.listFiles();
        File[] duplicateList = duplicates.listFiles();

        // Declare a variable to write to the output CSV file
        PrintWriter output = new PrintWriter("src/Evaluation/output.csv");

        // Check that the duplicateList and originList is not undefined
        assert duplicateList != null;
        assert originalList != null;

        // For every file in the duplicate-list
        for (File duplicate : duplicateList) {
            // If the duplicate is a file
            if (duplicate.isFile()) {
                // Output the file-name to the csv file
                output.print(duplicate.getName() + ",");

                // For every file in the origin-list
                for (File origin : originalList) {
                    // If it is a file, then calculate the duplicate's plagiarism against this one and write it to the CSV
                    if (origin.isFile()) {
                        double value = DriverProgram.driverProgram(origin, duplicate);
                        output.print(value + ",");
                    }
                }

                // Move to the next line.
                output.print("\n");
            }
        }

        // Close the output file
        output.close();
    }
}
