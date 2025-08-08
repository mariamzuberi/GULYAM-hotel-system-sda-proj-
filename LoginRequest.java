package hotel.management.system;

import javax.swing.*;

public class LoginRequest {
    String username;
    String password;
    JFrame frame;

    public LoginRequest(String username, String password, JFrame frame) {
        this.username = username;
        this.password = password;
        this.frame = frame;
    }
}
