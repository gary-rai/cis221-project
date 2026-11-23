package frontend;

import java.util.Scanner;

public class LogWatch {

public static void main(String[] args) {

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

securityLogin.login(email, "127.0.0.1", "SUCCESS");

int choice = 0;

while (choice != 2) {

System.out.println("\n===== LogWatch Menu =====");
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

securityLogin.login(email, "127.0.0.1", "FAILED");

}

scanner.close();

}

}