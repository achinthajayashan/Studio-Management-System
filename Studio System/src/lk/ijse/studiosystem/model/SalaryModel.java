package lk.ijse.studiosystem.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.studiosystem.to.Salary;
import lk.ijse.studiosystem.to.Suplier;
import lk.ijse.studiosystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SalaryModel {
    public static ObservableList loadEmpsid() throws SQLException, ClassNotFoundException {
        String sql="select employee_ID from employee ";
        ResultSet resultSet= CrudUtil.execute(sql);

        ObservableList observableList = FXCollections.observableArrayList();

        while (resultSet.next()){
            observableList.add(resultSet.getString(1));
        }
        return observableList;
    }
    public static boolean save(Salary salary) throws SQLException, ClassNotFoundException {
        /*PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO Customer VALUES(?, ?, ?, ?)");
        pstm.setString(1, customer.getId());
        pstm.setString(2, customer.getName());
        pstm.setString(3, customer.getAddress());
        pstm.setDouble(4, customer.getSalary());

        return pstm.executeUpdate() > 0;*/
        String sql = "INSERT INTO salary VALUES (?, ?, ?, ?)";
        return CrudUtil.execute(sql, salary.getSalary_id(), salary.getMonth(), salary.getValue(), salary.getEmp_id());
    }

    public static Salary search(String id) throws SQLException, ClassNotFoundException {
//        PreparedStatement pstm = DBConnection.getInstance().getConnection()
//                .prepareStatement("SELECT  * FROM Customer WHERE id = ?");
//        pstm.setObject(1, id);
//
//        ResultSet result = pstm.executeQuery();
        String sql = "SELECT  * FROM salary WHERE salary_ID = ?";
        ResultSet result = CrudUtil.execute(sql, id);

        if (result.next()) {
            return new Salary(
                    result.getString(1),
                    result.getString(2),
                    result.getDouble(3),
                    result.getString(4)
            );
        }
        return null;
    }

    public static boolean update(Salary salary) throws SQLException, ClassNotFoundException {
        /*PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO Customer VALUES(?, ?, ?, ?)");
        pstm.setString(1, customer.getId());
        pstm.setString(2, customer.getName());
        pstm.setString(3, customer.getAddress());
        pstm.setDouble(4, customer.getSalary());

        return pstm.executeUpdate() > 0;*/
        String sql = "UPDATE salary SET month=? ,value=? ,employee_ID=? WHERE salary_ID=?";
        return CrudUtil.execute(sql, salary.getMonth(), salary.getValue(), salary.getEmp_id(), salary.getSalary_id());
    }

    public static boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM salary WHERE salary_ID = ?";

        Boolean bool = CrudUtil.execute(sql, id);
        return bool;
    }
}
