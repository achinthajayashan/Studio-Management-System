package lk.ijse.studiosystem.to;

import java.time.LocalDate;
import java.util.Date;

public class Order {
    private String orderID;
    private LocalDate date;
    private String customerID;

    public Order(String orderId, LocalDate now, String customerId) {
        this.orderID=orderId;
        this.date=now;
        this.customerID=customerId;
    }

    public Order(String orderID, Date date, String customerID) {

    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", date='" + date + '\'' +
                ", customerID='" + customerID + '\'' +
                '}';
    }
}
