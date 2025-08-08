package hotel.management.system;

import javax.swing.*;
import java.sql.*;

// Handler to check if the username exists in the database
public class UsernameExistsHandler implements LoginHandler {
    private LoginHandler next;

    // Set the next handler in the chain
    @Override
    public void setNext(LoginHandler handler) {
        next = handler;
    }

    // Handle login request
    @Override
    public void handle(LoginRequest request) throws Exception {
        conn c = conn.getInstance();
        Statement s = c.getStatement();

        String query = "SELECT * FROM login WHERE username = '" + request.username + "'";
        ResultSet rs = s.executeQuery(query);

        if (rs.next()) {
            // If username exists, pass to the next handler
            if (next != null) {
                next.handle(request);
            }
        } else {
            // Show error if username is not found
            JOptionPane.showMessageDialog(request.frame, "Username does not exist");
        }
    }
}
