package hotel.management.system;

public class NullUser implements User {
    @Override
    public boolean isNull() {
        return true;
    }
}
