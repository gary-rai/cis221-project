
package frontend;

import java.util.Scanner;

public class LogAnalyzer {

public String analyze(String logText) {

int totalLogins = 0;
int successfulLogins = 0;
int failedLogins = 0;

// Store the individual login records
StringBuilder records = new StringBuilder();

Scanner scanner = new Scanner(logText);

while (scanner.hasNextLine()) {

String line = scanner.nextLine().trim();

// Skip empty lines
if (line.isEmpty()) {
continue;
}

// Find the separators between the log information
int firstSeparator = line.indexOf("|");
int secondSeparator = line.indexOf("|", firstSeparator + 1);
int thirdSeparator = line.indexOf("|", secondSeparator + 1);

// Skip lines that do not have all four fields
if (firstSeparator == -1 ||
secondSeparator == -1 ||
thirdSeparator == -1) {

continue;

}

// Separate the login information
String timestamp = line.substring(0, firstSeparator).trim();

String email = line.substring(firstSeparator + 1, secondSeparator).trim();

String ip = line.substring(secondSeparator + 1, thirdSeparator).trim();

String status = line.substring(thirdSeparator + 1).trim();

// Count successful and failed logins
if (status.equals("SUCCESS")) {

successfulLogins++;
totalLogins++;

} else if (status.equals("FAILED")) {

failedLogins++;
totalLogins++;

} else {

continue;

}

// Add the login information to the records
if (records.length() > 0) {

records.append(",");

}

records.append("{")
.append("\"timestamp\":\"").append(jsonSafe(timestamp)).append("\",")
.append("\"email\":\"").append(jsonSafe(email)).append("\",")
.append("\"ip\":\"").append(jsonSafe(ip)).append("\",")
.append("\"status\":\"").append(jsonSafe(status)).append("\"")
.append("}");

}

scanner.close();

// Create the final JSON response
String results = "{"
+ "\"totalLogins\":" + totalLogins + ","
+ "\"successfulLogins\":" + successfulLogins + ","
+ "\"failedLogins\":" + failedLogins + ","
+ "\"records\":[" + records + "]"
+ "}";

return results;

}

// Prepare text so it can safely be included in JSON
public String jsonSafe(String text) {

return text.replace("\\", "\\\\")
.replace("\"", "\\\"")
.replace("\n", "\\n")
.replace("\r", "\\r");

}

}
