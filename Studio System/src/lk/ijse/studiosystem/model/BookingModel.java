package lk.ijse.studiosystem.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.studiosystem.to.Booking;
import lk.ijse.studiosystem.to.Packages;
import lk.ijse.studiosystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingModel {
    public static ObservableList loadCustid() throws SQLException, ClassNotFoundException {
        String sql="select customer_ID from customer ";
        ResultSet resultSet= CrudUtil.execute(sql);

        ObservableList observableList = FXCollections.observableArrayList();

        while (resultSet.next()){
            observableList.add(resultSet.getString(1));
        }
        return observableList;
    }

    public static ObservableList loadpkgsid() throws SQLException, ClassNotFoundException {
        String sql="select package_ID from package ";
        ResultSet resultSet= CrudUtil.execute(sql);

        ObservableList observableList = FXCollections.observableArrayList();

        while (resultSet.next()){
            observableList.add(resultSet.getString(1));
        }
        return observableList;
    }

    public static boolean save(Booking booking) throws SQLException, ClassNotFoundException {
        /*PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO Customer VALUES(?, ?, ?, ?)");
        pstm.setString(1, customer.getId());
        pstm.setString(2, customer.getName());
        pstm.setString(3, customer.getAddress());
        pstm.setDouble(4, customer.getSalary());

        return pstm.executeUpdate() > 0;*/
        String sql = "INSERT INTO book VALUES (?, ?, ?, ? ,?)";
        return CrudUtil.execute(sql, booking.getBooking_id(),booking.getLocation(),booking.getDate(),booking.getCustomer_id(),booking.getPackage_id());
    }

    public static Booking search(String id) throws SQLException, ClassNotFoundException {
//        PreparedStatement pstm = DBConnection.getInstance().getConnection()
//                .prepareStatement("SELECT  * FROM Customer WHERE id = ?");
//        pstm.setObject(1, id);
//
//        ResultSet result = pstm.executeQuery();
        String sql = "SELECT  * FROM book WHERE booking_ID = ?";
        ResultSet result = CrudUtil.execute(sql, id);

        if(result.next()) {
            return new Booking(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5)
            );
        }
        return null;
    }
    public static boolean update(Booking booking) throws SQLException, ClassNotFoundException {
        /*PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO Customer VALUES(?, ?, ?, ?)");
        pstm.setString(1, customer.getId());
        pstm.setString(2, customer.getName());
        pstm.setString(3, customer.getAddress());
        pstm.setDouble(4, customer.getSalary());

        return pstm.executeUpdate() > 0;*/
        String sql = "UPDATE book SET location=? ,date=? ,customer_ID=? ,package_ID=?WHERE booking_ID=?";
        return CrudUtil.execute(sql, booking.getLocation(),booking.getDate(),booking.getCustomer_id(),booking.getPackage_id(),booking.getBooking_id());
    }

    public static boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM book WHERE booking_ID = ?";

        Boolean bool = CrudUtil.execute(sql,id);
        return  bool;
    }
}
