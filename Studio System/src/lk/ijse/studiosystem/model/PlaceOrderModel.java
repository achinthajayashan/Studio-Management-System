package lk.ijse.studiosystem.model;

import javafx.scene.control.Alert;
import lk.ijse.studiosystem.db.DBConnection;
import lk.ijse.studiosystem.to.Order;
import lk.ijse.studiosystem.to.PlaceOrder;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class PlaceOrderModel {
    public static boolean placeOrder(PlaceOrder placeOrder) throws SQLException, ClassNotFoundException {
       try{
           DBConnection.getInstance().getConnection().setAutoCommit(false);
           boolean isOrderAdded = OrderModel.saveOrder(new Order(placeOrder.getOrderId(), LocalDate.now(), placeOrder.getCustomerId()));
           if (isOrderAdded) {
                boolean isUpdated = ItemModel.updateQty(placeOrder.getOrderDetails());
                    if (isUpdated) {
                        boolean isOrderDetailAdded = OrderDetailModel.saveOrderDetails(placeOrder.getOrderDetails());
                        System.out.println("order detail placed");
                            if (isOrderDetailAdded) {
                                System.out.println("Order Placed !");
                                DBConnection.getInstance().getConnection().commit();
                                return true;
                            }
                    }
           }
            DBConnection.getInstance().getConnection().rollback();
            return false;
        }finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
            return true;
        }
    }
}
