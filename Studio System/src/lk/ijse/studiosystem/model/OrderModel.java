package lk.ijse.studiosystem.model;

import javafx.collections.ObservableList;
import lk.ijse.studiosystem.to.Order;
import lk.ijse.studiosystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderModel {

    public static String generateNextOrderId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT order_ID FROM Orders ORDER BY order_ID DESC LIMIT 1";
        ResultSet result = CrudUtil.execute(sql);

        if (result.next()) {
            return generateNextOrderId(result.getString(1));
        }
        return generateNextOrderId(result.getString(null));
    }

    private static String generateNextOrderId(String currentOrderId) {
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("O");
            int id = Integer.parseInt(split[1]);
            id += 1;
            return "O" + id;
        }
        return "O001";

    }

    public static boolean saveOrder(Order order) throws SQLException, ClassNotFoundException {
        String sql="insert into orders Values (?,?,?)";
        return CrudUtil.execute(sql,order.getOrderID(),order.getDate(),order.getCustomerID());
    }

    public static ArrayList<String> loadOrderIds() throws SQLException, ClassNotFoundException {
        String sql = "SELECT order_ID FROM orders";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> idList = new ArrayList<>();

        while (result.next()) {
            idList.add(result.getString(1));
        }
        return idList;
    }
}
