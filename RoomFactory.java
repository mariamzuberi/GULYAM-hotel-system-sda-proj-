package hotel.management.system;

public class RoomFactory {
    public static Roomm createRoom(String roomNumber, String availability, String cleaningStatus, String price, String bedType) {
        return new Roomm(roomNumber, availability, cleaningStatus, price, bedType);
    }
}
