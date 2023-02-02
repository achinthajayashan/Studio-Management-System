package lk.ijse.studiosystem.model;

import lk.ijse.studiosystem.to.CartDetail;
import lk.ijse.studiosystem.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailModel {
    public static boolean saveOrderDetails(ArrayList<CartDetail> cartDetails) throws SQLException, ClassNotFoundException {
        for (CartDetail cartDetail : cartDetails) {
            if (!saveDetail(cartDetail)) {
                return false;
            }
        }
        return true;
    }

    private static boolean saveDetail(CartDetail cartDetail) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Order_detail VALUES(?, ?, ?, ?)";
        return CrudUtil.execute(sql,cartDetail.getCode(), cartDetail.getOrderId(), cartDetail.getQty(), cartDetail.getUnitPrice());
    }
}
