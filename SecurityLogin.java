import java.io.FileWriter;
import java.time.LocalDateTime;

public class SecurityLogin {

    public void login(String email, String ip, String success) {

        try {
            FileWriter data = new FileWriter("SecurityReport.txt", true);

            data.write(LocalDateTime.now() + " | " + email + " | " + ip + " | " + success + "\n");

            data.close();
        }

        catch (Exception e) {
            System.out.println("ERROR");
        }

    }
}
