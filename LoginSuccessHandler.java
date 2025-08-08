package hotel.management.system;

public class LoginSuccessHandler implements LoginHandler {
    public void setNext(LoginHandler handler) {
        // Null Object Pattern — no next handler
    }

    public void handle(LoginRequest request) {
        Dashboard.getInstance().setVisible(true);
        request.frame.setVisible(false);
    }
}
