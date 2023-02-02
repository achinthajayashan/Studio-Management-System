package lk.ijse.studiosystem.to;

import java.time.LocalDate;

public class Payment {
    private String payment_id;
    private double value;
    private String date;
    private String payment_type;
    private String order_id;
    private String booking_id;

    public Payment(String id, double value, LocalDate date, String type, String orderId, String bookingId) {
    }

    public Payment(String payment_id, double value, String date, String payment_type, String order_id, String booking_id) {
        this.payment_id = payment_id;
        this.value = value;
        this.date = date;
        this.payment_type = payment_type;
        this.order_id = order_id;
        this.booking_id = booking_id;
    }

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }
}
