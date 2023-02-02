package lk.ijse.studiosystem.model;

import lk.ijse.studiosystem.to.Customer;
import lk.ijse.studiosystem.to.Employee;
import lk.ijse.studiosystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeModel {
    public static boolean save(Employee employee) throws SQLException, ClassNotFoundException {
        /*PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO Customer VALUES(?, ?, ?, ?)");
        pstm.setString(1, customer.getId());
        pstm.setString(2, customer.getName());
        pstm.setString(3, customer.getAddress());
        pstm.setDouble(4, customer.getSalary());

        return pstm.executeUpdate() > 0;*/
        String sql = "INSERT INTO employee VALUES (?, ?, ?, ?, ?,?)";
        return CrudUtil.execute(sql, employee.getEmpID(), employee.getEmpName(), employee.getEmpNic(), employee.getEmpDob(), employee.getEmpContact(),employee.getEmpEmail());
    }

    public static Employee search(String id) throws SQLException, ClassNotFoundException {
//        PreparedStatement pstm = DBConnection.getInstance().getConnection()
//                .prepareStatement("SELECT  * FROM Customer WHERE id = ?");
//        pstm.setObject(1, id);
//
//        ResultSet result = pstm.executeQuery();
        String sql = "SELECT  * FROM employee WHERE employee_ID = ?";
        ResultSet result = CrudUtil.execute(sql, id);

        if(result.next()) {
            return new Employee(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getInt(5),
                    result.getString(6)
            );
        }
        return null;
    }
    public static boolean update(Employee employee) throws SQLException, ClassNotFoundException {
        /*PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO Customer VALUES(?, ?, ?, ?)");
        pstm.setString(1, customer.getId());
        pstm.setString(2, customer.getName());
        pstm.setString(3, customer.getAddress());
        pstm.setDouble(4, customer.getSalary());

        return pstm.executeUpdate() > 0;*/
        String sql = "UPDATE Employee SET emp_name=? ,emp_NIC=? ,emp_dob=? ,emp_contact=? ,emp_email=? WHERE employee_ID=?";
        return CrudUtil.execute(sql, employee.getEmpName(), employee.getEmpNic(), employee.getEmpDob(), employee.getEmpContact(), employee.getEmpEmail(),employee.getEmpID());
    }

    public static boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM employee WHERE employee_ID = ?";

        Boolean bool = CrudUtil.execute(sql,id);
        return  bool;
    }
}
