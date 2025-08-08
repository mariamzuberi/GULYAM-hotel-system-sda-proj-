package hotel.management.system;

public interface LoginHandler {
    void setNext(LoginHandler handler);
    void handle(LoginRequest request) throws Exception;
}
