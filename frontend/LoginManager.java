package frontend;
import java.io.File;
import java.util.Scanner;

public class LoginManager {

public LoginManager() {

    System.out.println("Login Manager has Started");

}

public boolean login(String email, String password) {

    try {
        File file = new File("users.txt");
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            
            String loginInfo = email + "|" + password;

                if (line.equals(loginInfo)) {

                scanner.close();
    return true;

}

        }
        scanner.close();
    } 
    
    catch (Exception e) {
        System.out.println("Error reading the user.txt file");
    }

    return false;
}



}