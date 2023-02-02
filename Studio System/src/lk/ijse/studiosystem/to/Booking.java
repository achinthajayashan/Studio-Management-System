package lk.ijse.studiosystem.to;

public class Booking {
    private String booking_id;
    private String location;
    private String date;
    private String customer_id;
    private String package_id;

    public Booking() {
    }

    public Booking(String booking_id, String location, String date, String customer_id, String package_id) {
        this.booking_id = booking_id;
        this.location = location;
        this.date = date;
        this.customer_id = customer_id;
        this.package_id = package_id;
    }

    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getPackage_id() {
        return package_id;
    }

    public void setPackage_id(String package_id) {
        this.package_id = package_id;
    }
}
