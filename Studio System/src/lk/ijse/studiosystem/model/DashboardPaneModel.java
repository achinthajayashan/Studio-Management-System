package lk.ijse.studiosystem.model;

import lk.ijse.studiosystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardPaneModel {
    public static int getCusCount() throws SQLException, ClassNotFoundException {
        String sql="select count(1) from customer";
        ResultSet resultSet = CrudUtil.execute(sql);

        while(resultSet.next()){
            return resultSet.getInt(1);
        }
        return Integer.parseInt(null);
    }

    public static int getItemCount() throws SQLException, ClassNotFoundException {
        String sql="select count(1) from item";
        ResultSet resultSet = CrudUtil.execute(sql);

        while(resultSet.next()){
            return resultSet.getInt(1);
        }
        return Integer.parseInt(null);
    }

    public static int getOrderCount() throws SQLException, ClassNotFoundException {
        String sql="select count(1) from orders";
        ResultSet resultSet = CrudUtil.execute(sql);

        while(resultSet.next()){
            return resultSet.getInt(1);
        }
        return Integer.parseInt(null);
    }

    public static int getSupplierCount() throws SQLException, ClassNotFoundException {
        String sql="select count(1) from supplier";
        ResultSet resultSet = CrudUtil.execute(sql);

        while(resultSet.next()){
            return resultSet.getInt(1);
        }
        return Integer.parseInt(null);
    }
}
