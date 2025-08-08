package hotel.management.system;

public class RealUser implements User {
    private String username;

    public RealUser(String username) {
        this.username = username;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    public String getUsername() {
        return username;
    }
}
