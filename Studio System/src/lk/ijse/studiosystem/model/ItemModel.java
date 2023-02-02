package lk.ijse.studiosystem.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.studiosystem.to.CartDetail;
import lk.ijse.studiosystem.to.CartDetail;
import lk.ijse.studiosystem.to.Customer;
import lk.ijse.studiosystem.to.Item;
import lk.ijse.studiosystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemModel {
    public static boolean save(Item item) throws SQLException, ClassNotFoundException {
        /*PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO Customer VALUES(?, ?, ?, ?)");
        pstm.setString(1, customer.getId());
        pstm.setString(2, customer.getName());
        pstm.setString(3, customer.getAddress());
        pstm.setDouble(4, customer.getSalary());

        return pstm.executeUpdate() > 0;*/
        String sql = "INSERT INTO item VALUES (?, ?, ?, ?, ?)";
        return CrudUtil.execute(sql, item.getItem_ID(), item.getItem_name(), item.getUnit_price(),item.getQty_on_hnd(),item.getSupplier_ID());
    }

    public static Item search(String id) throws SQLException, ClassNotFoundException {
//        PreparedStatement pstm = DBConnection.getInstance().getConnection()
//                .prepareStatement("SELECT  * FROM Customer WHERE id = ?");
//        pstm.setObject(1, id);
//
//        ResultSet result = pstm.executeQuery();
        String sql = "SELECT  * FROM item WHERE item_ID = ?";
        ResultSet result = CrudUtil.execute(sql, id);

        if(result.next()) {
            return new Item(
                    result.getString(1),
                    result.getString(2),
                    result.getDouble(3),
                    result.getInt(4),
                    result.getString(5)
            );
        }
        return null;
    }
    public static boolean update(Item item) throws SQLException, ClassNotFoundException {
        /*PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO Customer VALUES(?, ?, ?, ?)");
        pstm.setString(1, customer.getId());
        pstm.setString(2, customer.getName());
        pstm.setString(3, customer.getAddress());
        pstm.setDouble(4, customer.getSalary());

        return pstm.executeUpdate() > 0;*/
        String sql = "UPDATE Item SET item_name=? ,unit_price=? ,qty_on_hnd=?, supplier_ID=? WHERE item_ID=?";
        return CrudUtil.execute(sql,  item.getItem_name(), item.getUnit_price(),item.getQty_on_hnd(),item.getSupplier_ID(),item.getItem_ID());
    }

    public static boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM item WHERE item_ID = ?";

        Boolean bool = CrudUtil.execute(sql,id);
        return  bool;
    }

    public static ObservableList loadSupids() throws SQLException, ClassNotFoundException {
        String sql="select supplier_ID from supplier ";
        ResultSet resultSet= CrudUtil.execute(sql);

        ObservableList observableList = FXCollections.observableArrayList();

        while (resultSet.next()){
            observableList.add(resultSet.getString(1));
        }
        return observableList;
    }

    public static boolean updateQty(ArrayList<CartDetail> cartDetails) throws SQLException, ClassNotFoundException {
        for (CartDetail cartDetail : cartDetails) {
            if (!updateQty(new Item(cartDetail.getCode(), cartDetail.getDescription(), cartDetail.getUnitPrice(), cartDetail.getQty()))) {
                return false;
            }
        }
        return true;
    }

    private static boolean updateQty(Item item) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Item SET qty_on_hnd = qty_on_hnd - ? WHERE item_ID = ?";
        return CrudUtil.execute(sql, item.getQty_on_hnd(), item.getItem_ID());
    }

    public static ArrayList<String> loadItemCodes() throws SQLException, ClassNotFoundException {
        String sql = "SELECT item_ID FROM item";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> codeList = new ArrayList<>();

        while (result.next()) {
            codeList.add(result.getString(1));
        }
        return codeList;
    }
}
