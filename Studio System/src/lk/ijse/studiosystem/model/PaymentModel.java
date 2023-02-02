package lk.ijse.studiosystem.model;

import lk.ijse.studiosystem.to.Customer;
import lk.ijse.studiosystem.to.Payment;
import lk.ijse.studiosystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentModel {
    public static boolean save(Payment payment) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO payment VALUES (?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(sql,payment.getPayment_id(),payment.getValue(),payment.getDate(),payment.getPayment_type(),payment.getOrder_id(),payment.getBooking_id());
    }

    public static Payment search(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM payment WHERE payment_ID = ?";
        ResultSet result = CrudUtil.execute(sql, id);

        if(result.next()) {
            return new Payment(
                    result.getString(1),
                    result.getDouble(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getString(6)
            );
        }
        return null;
    }


}
