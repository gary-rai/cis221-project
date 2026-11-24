package frontend;

import java.net.InetAddress;

import java.util.Scanner;

public class LogWatch {

public static void main(String[] args) {

 String ipaddress = "UNKNOWN";

try {
    for (java.net.NetworkInterface n : java.util.Collections.list(
            java.net.NetworkInterface.getNetworkInterfaces())) {

        for (java.net.InetAddress a : java.util.Collections.list(n.getInetAddresses())) {

            if (a instanceof java.net.Inet4Address && !a.isLoopbackAddress()) {
                ipaddress = a.getHostAddress();
                break;
            }
        }

        if (!ipaddress.equals("UNKNOWN")) 
            break;
    }

} catch (Exception e) {
    System.out.println("Could not get IP");
}


Scanner scanner = new Scanner(System.in);

System.out.println("===== Welcome to LogWatch =====");



System.out.print("Enter email: ");
String email = scanner.nextLine();

System.out.print("Enter password: ");
String password = scanner.nextLine();

LoginManager manager = new LoginManager();

boolean success = manager.login(email, password);

SecurityLogin securityLogin = new SecurityLogin();

if (success) {

System.out.println("Login Successful");

securityLogin.login(email, ipaddress, "SUCCESS");

int choice = 0;

while (choice != 2) {

System.out.println("===== LogWatch Menu =====\n");
System.out.println("1. Analyze Security Logs");
System.out.println("2. Logout");

System.out.print("Enter your choice: ");
choice = scanner.nextInt();

if (choice == 1) {

LogAnalyzer analyzer = new LogAnalyzer();
analyzer.analyze();

} else if (choice == 2) {

System.out.println("Logged out successfully");

} else {

System.out.println("Invalid choice");

}

}

} else {

System.out.println("Login Failed. Access Denied.");

securityLogin.login(email, ipaddress, "FAILED");

}

scanner.close();

}
}
