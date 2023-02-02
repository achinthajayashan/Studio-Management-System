package lk.ijse.studiosystem.model;

import lk.ijse.studiosystem.to.Item;
import lk.ijse.studiosystem.to.Packages;
import lk.ijse.studiosystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PackageModel {
    public static boolean save(Packages packages) throws SQLException, ClassNotFoundException {
        /*PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO Customer VALUES(?, ?, ?, ?)");
        pstm.setString(1, customer.getId());
        pstm.setString(2, customer.getName());
        pstm.setString(3, customer.getAddress());
        pstm.setDouble(4, customer.getSalary());

        return pstm.executeUpdate() > 0;*/
        String sql = "INSERT INTO package VALUES (?, ?, ?)";
        return CrudUtil.execute(sql, packages.getPackage_ID(),packages.getPackage_name(),packages.getPrice());
    }

    public static Packages search(String id) throws SQLException, ClassNotFoundException {
//        PreparedStatement pstm = DBConnection.getInstance().getConnection()
//                .prepareStatement("SELECT  * FROM Customer WHERE id = ?");
//        pstm.setObject(1, id);
//
//        ResultSet result = pstm.executeQuery();
        String sql = "SELECT  * FROM package WHERE package_ID = ?";
        ResultSet result = CrudUtil.execute(sql, id);

        if(result.next()) {
            return new Packages(
                    result.getString(1),
                    result.getString(2),
                    result.getDouble(3)
            );
        }
        return null;
    }
    public static boolean update(Packages packages) throws SQLException, ClassNotFoundException {
        /*PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO Customer VALUES(?, ?, ?, ?)");
        pstm.setString(1, customer.getId());
        pstm.setString(2, customer.getName());
        pstm.setString(3, customer.getAddress());
        pstm.setDouble(4, customer.getSalary());

        return pstm.executeUpdate() > 0;*/
        String sql = "UPDATE package SET package_name=? ,price=? WHERE package_ID=?";
        return CrudUtil.execute(sql, packages.getPackage_name(),packages.getPrice(),packages.getPackage_ID());
    }

    public static boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM package WHERE package_ID = ?";

        Boolean bool = CrudUtil.execute(sql,id);
        return  bool;
    }
}
