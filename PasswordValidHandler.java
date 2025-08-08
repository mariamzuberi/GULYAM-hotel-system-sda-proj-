package hotel.management.system;

import javax.swing.*;
import java.sql.*;

public class PasswordValidHandler implements LoginHandler {
    private LoginHandler next;

    // Set the next handler in the chain
    public void setNext(LoginHandler handler) {
        next = handler;
    }

    // Handle the login request
    public void handle(LoginRequest request) throws Exception {
        conn c = conn.getInstance();
        Statement s = c.getStatement();  // Correct way to access the Statement

        String query = "SELECT * FROM login WHERE username = '" + request.username + "' AND password = '" + request.password + "'";
        ResultSet rs = s.executeQuery(query);  // Use the statement from getStatement()

        if (rs.next()) {
            if (next != null) {
                next.handle(request);  // Pass to next handler
            }
        } else {
            JOptionPane.showMessageDialog(request.frame, "Invalid password");
        }
    }
}
