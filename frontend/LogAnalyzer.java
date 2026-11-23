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

while (scanner.hasNextLine()) {

String line = scanner.nextLine();

String[] details = line.split("\\|");

if (details.length == 4) {

String status = details[3].trim();

if (status.equals("SUCCESS")) {

successfulLogins++;

} else if (status.equals("FAILED")) {

failedLogins++;

}

}

}

scanner.close();

int totalLogins = successfulLogins + failedLogins;

System.out.println("\n===== Security Report =====");
System.out.println("Total Login Attempts: " + totalLogins);
System.out.println("Successful Logins: " + successfulLogins);
System.out.println("Failed Logins: " + failedLogins);

} catch (Exception e) {

System.out.println("Error reading SecurityReport.txt");

}

}

}