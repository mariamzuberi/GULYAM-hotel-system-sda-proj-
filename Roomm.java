package hotel.management.system;

public class Roomm {
    public String roomNumber;
    public String availability;
    public String cleaningStatus;
    public String price;
    public String bedType;

    public Roomm(String roomNumber, String availability, String cleaningStatus, String price, String bedType) {
        this.roomNumber = roomNumber;
        this.availability = availability;
        this.cleaningStatus = cleaningStatus;
        this.price = price;
        this.bedType = bedType;
    }
}
