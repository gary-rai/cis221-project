
package frontend;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;

public class LoginServer {

public static void main(String[] args) throws Exception {

HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", 9090), 0);

// Handle login requests
server.createContext("/api/login", exchange -> {

try {

if (!exchange.getRequestMethod().equals("POST")) {

exchange.sendResponseHeaders(405, -1);
return;

}

// Read email and password
InputStream input = exchange.getRequestBody();

String loginInfo = new String(input.readAllBytes(), StandardCharsets.UTF_8);

int separator = loginInfo.indexOf("\n");

if (separator == -1) {

exchange.sendResponseHeaders(400, -1);
return;

}

String email = loginInfo.substring(0, separator).trim();
String password = loginInfo.substring(separator + 1);

// Check credentials
LoginManager manager = new LoginManager();

boolean success = manager.login(email, password);

String response = "Login Failed";

if (success) {

response = "Login Successful";

}

byte[] data = response.getBytes(StandardCharsets.UTF_8);

exchange.getResponseHeaders().set("Content-Type", "text/plain; charset=UTF-8");

exchange.sendResponseHeaders(200, data.length);

exchange.getResponseBody().write(data);

} catch (Exception e) {

e.printStackTrace();

} finally {

exchange.close();

}

});

// Serve the website files
server.createContext("/", exchange -> {

try {

String path = exchange.getRequestURI().getPath();

if (path.equals("/")) {

path = "/login.html";

}

// Only allow known website files
if (!path.equals("/login.html") &&
!path.equals("/dashboard.html") &&
!path.equals("/style.css") &&
!path.equals("/dashboard.css") &&
!path.equals("/script.js") &&
!path.equals("/images/logo-watch.png")) {

exchange.sendResponseHeaders(404, -1);
return;

}

File file = new File("frontend" + path);

if (!file.exists()) {

exchange.sendResponseHeaders(404, -1);
return;

}

String contentType = "text/plain";

if (path.endsWith(".html")) {

contentType = "text/html";

} else if (path.endsWith(".css")) {

contentType = "text/css";

} else if (path.endsWith(".js")) {

contentType = "application/javascript";

} else if (path.endsWith(".png")) {

contentType = "image/png";

}

byte[] data = Files.readAllBytes(file.toPath());

exchange.getResponseHeaders().set("Content-Type", contentType);

exchange.sendResponseHeaders(200, data.length);

exchange.getResponseBody().write(data);

} catch (Exception e) {

e.printStackTrace();

} finally {

exchange.close();

}

});

server.start();

System.out.println("LogWatch server running at http://127.0.0.1:9090");

}

}
