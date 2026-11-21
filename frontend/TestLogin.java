package frontend;

public class TestLogin {

public static void main(String[] args) {

LoginManager manager = new LoginManager();

boolean success = manager.login("admin@logwatch.com", "Test1234");

if (success) {

System.out.println("Login Successful!");

} else {

System.out.println("Login Failed!");

}

}

}
