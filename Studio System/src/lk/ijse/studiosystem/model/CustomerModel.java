package lk.ijse.studiosystem.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.studiosystem.db.DBConnection;
import lk.ijse.studiosystem.to.Customer;
import lk.ijse.studiosystem.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {
    public static boolean save(Customer customer) throws SQLException, ClassNotFoundException {
        /*PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO Customer VALUES(?, ?, ?, ?)");
        pstm.setString(1, customer.getId());
        pstm.setString(2, customer.getName());
        pstm.setString(3, customer.getAddress());
        pstm.setDouble(4, customer.getSalary());

        return pstm.executeUpdate() > 0;*/
        String sql = "INSERT INTO customer VALUES (?, ?, ?, ?, ?)";
        return CrudUtil.execute(sql, customer.getCustomerID(), customer.getCustomerName(), customer.getCustomerAddress(), customer.getCustomerContact(), customer.getCustomerEmail());
    }

    public static Customer search(String id) throws SQLException, ClassNotFoundException {
//        PreparedStatement pstm = DBConnection.getInstance().getConnection()
//                .prepareStatement("SELECT  * FROM Customer WHERE id = ?");
//        pstm.setObject(1, id);
//
//        ResultSet result = pstm.executeQuery();
        String sql = "SELECT  * FROM customer WHERE customer_ID = ?";
        ResultSet result = CrudUtil.execute(sql, id);

        if(result.next()) {
            return new Customer(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5)
            );
        }
        return null;
    }
    public static boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        /*PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO Customer VALUES(?, ?, ?, ?)");
        pstm.setString(1, customer.getId());
        pstm.setString(2, customer.getName());
        pstm.setString(3, customer.getAddress());
        pstm.setDouble(4, customer.getSalary());

        return pstm.executeUpdate() > 0;*/
        String sql = "UPDATE Customer SET customer_name=? ,address=? ,contact=? ,cus_email=? WHERE customer_ID=?";
        return CrudUtil.execute(sql, customer.getCustomerName(), customer.getCustomerAddress(), customer.getCustomerContact(), customer.getCustomerEmail(),customer.getCustomerID());
    }

    public static boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM customer WHERE customer_ID = ?";

        Boolean bool = CrudUtil.execute(sql,id);
        return  bool;
    }

    public static ArrayList<String> loadCustomerIds() throws SQLException, ClassNotFoundException {
        String sql = "SELECT customer_id FROM customer";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> idList = new ArrayList<>();

        while (result.next()) {
            idList.add(result.getString(1));
        }
        return idList;
    }

    // public static boolean delete(){}
}
