package frontend;

import java.io.File;
import java.util.Scanner;

public class LogAnalyzer {

public void analyze() {

        int successfulLogins = 0;
        int failedLogins = 0;

        try {

            File file = new File("SecurityReport.txt");
            Scanner scanner = new Scanner(file);

            System.out.println("\n========== FULL SECURITY REPORT ==========\n");

            // Display the entire text file
            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();

                System.out.println(line);

                // Count successful and failed attempts
                if (line.contains("SUCCESS")) {
                    successfulLogins++;
                }
                else if (line.contains("FAILED")) {
                    failedLogins++;
                }
            }

            scanner.close();

            // Calculate total attempts
            int totalLogins = successfulLogins + failedLogins;

            // Display summary separately
            System.out.println("========== LOGIN SUMMARY ==========\n");
            System.out.println("Successful Attempts: " + successfulLogins);
            System.out.println("Failed Attempts: " + failedLogins);
            System.out.println("Total Login Attempts: " + totalLogins);

            System.out.println("===================================");

        } 
        catch (Exception e) {

            System.out.println("Error reading SecurityReport.txt");

        }
    }
}