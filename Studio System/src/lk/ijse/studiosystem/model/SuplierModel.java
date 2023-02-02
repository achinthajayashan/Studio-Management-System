package lk.ijse.studiosystem.model;

import lk.ijse.studiosystem.to.Customer;
import lk.ijse.studiosystem.to.Employee;
import lk.ijse.studiosystem.to.Suplier;
import lk.ijse.studiosystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SuplierModel {
    public static boolean save(Suplier suplier) throws SQLException, ClassNotFoundException {
        /*PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO Customer VALUES(?, ?, ?, ?)");
        pstm.setString(1, customer.getId());
        pstm.setString(2, customer.getName());
        pstm.setString(3, customer.getAddress());
        pstm.setDouble(4, customer.getSalary());

        return pstm.executeUpdate() > 0;*/
        String sql = "INSERT INTO supplier VALUES (?, ?, ?, ?)";
        return CrudUtil.execute(sql,suplier.getSupplier_ID(),suplier.getSupplier_name(),suplier.getAddress(),suplier.getContact());
    }

    public static Suplier search(String id) throws SQLException, ClassNotFoundException {
//        PreparedStatement pstm = DBConnection.getInstance().getConnection()
//                .prepareStatement("SELECT  * FROM Customer WHERE id = ?");
//        pstm.setObject(1, id);
//
//        ResultSet result = pstm.executeQuery();
        String sql = "SELECT  * FROM supplier WHERE supplier_ID = ?";
        ResultSet result = CrudUtil.execute(sql, id);

        if(result.next()) {
            return new Suplier(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getInt(4)
            );
        }
        return null;
    }
    public static boolean update(Suplier suplier) throws SQLException, ClassNotFoundException {
        /*PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO Customer VALUES(?, ?, ?, ?)");
        pstm.setString(1, customer.getId());
        pstm.setString(2, customer.getName());
        pstm.setString(3, customer.getAddress());
        pstm.setDouble(4, customer.getSalary());

        return pstm.executeUpdate() > 0;*/
        String sql = "UPDATE Supplier SET supplier_name=? ,address=? ,contact=? WHERE supplier_ID=?";
        return CrudUtil.execute(sql,suplier.getSupplier_name(),suplier.getAddress(),suplier.getContact(),suplier.getSupplier_ID());
    }

    public static boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Supplier WHERE supplier_ID = ?";

        Boolean bool = CrudUtil.execute(sql,id);
        return  bool;
    }
}
