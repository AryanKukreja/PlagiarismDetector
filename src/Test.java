/*
 * Author     - Aryan Kukreja | Student #: 100651838
 * Date Made  - Tuesday, November 26, 2019 @ 8:07 PM
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

import java.io.File;
import java.io.PrintWriter;

public class Test {
    public static void main(String[] args) throws Exception {
        File originals = new File("src/Evaluation/Original");
        File[] originalList = originals.listFiles();
        File duplicates = new File("src/Evaluation/Plagiarized");
        File[] duplicateList = duplicates.listFiles();

        PrintWriter output = new PrintWriter("src/Evaluation/output.csv");

        assert duplicateList != null;
        for (File duplicate : duplicateList) {
            if (duplicate.isFile()) {
                output.print(duplicate.getName() + ",");
                assert originalList != null;
                for (File origin : originalList) {
                    if (origin.isFile()) {
                        double value = DriverProgram.driverProgram(origin, duplicate);
                        output.print(value + ",");
                    }
                }
                output.print("\n");
            }
        }

        output.close();
    }
}
