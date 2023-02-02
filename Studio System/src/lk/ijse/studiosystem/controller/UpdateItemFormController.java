package lk.ijse.studiosystem.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.studiosystem.model.CustomerModel;
import lk.ijse.studiosystem.model.ItemModel;
import lk.ijse.studiosystem.to.Customer;
import lk.ijse.studiosystem.to.Item;

import java.sql.SQLException;

public class UpdateItemFormController {

    public AnchorPane ItemUpdateContext;
    public JFXTextField txtItemId;
    public JFXTextField txtItemName;
    public JFXTextField txtItemUnitPrice;
    public JFXTextField txtQtyOnHnd;
    public JFXComboBox cmdSuplierID;

    public void initialize(){
        loadSupId();
    }

    public void updateOnAction(ActionEvent actionEvent) {
        String itemId =txtItemId.getText();
        String itemName =txtItemName.getText();
        Double itemPrice = Double.parseDouble(txtItemUnitPrice.getText());
        int qty_on_hnd = Integer.parseInt(txtQtyOnHnd.getText());
        String supplier_ID = String.valueOf(cmdSuplierID.getValue());
//        String cusContact =txtCusContact.getText();
//        String cusEmail = txtCusEmail.getText();

        Item item = new Item(itemId,itemName,itemPrice,qty_on_hnd,supplier_ID);

        try {
            boolean isUpdated= ItemModel.update(item);
            if (isUpdated) {
                //clear();
//                loadAllCustomers();
                //new Alert(Alert.AlertType.CONFIRMATION, "Update Confirmed!").show();
                new Alert(Alert.AlertType.INFORMATION, "Updated Successfully !").show();
                closeStage(ItemUpdateContext);
//                CustomerFormController.tblCustomer.refresh();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }

        } catch (SQLException | ClassNotFoundException ignored) {}
        //.refresh();

        closeStage(ItemUpdateContext);
//        CustomerFormController customerFormController = new CustomerFormController();
//        customerFormController.getAllCustomers();
    }

    public void loadItemDetailsOnAction(ActionEvent actionEvent) {
        String id = txtItemId.getText();
        try {
            Item item = ItemModel.search(id);
            if(item != null) {
                fillData(item);
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String id = txtItemId.getText();
        try {
            boolean isDelete = ItemModel.delete(id);

            if(isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item Deleted!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void closeStage(AnchorPane anchorPane){
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
    }
    private void fillData(Item item) {
        txtItemId.setText(item.getItem_ID());
        txtItemName.setText(item.getItem_name());
        txtItemUnitPrice.setText(String.valueOf(item.getUnit_price()));
        txtQtyOnHnd.setText(String.valueOf(item.getQty_on_hnd()));
        cmdSuplierID.setValue(item.getSupplier_ID());
    }

    public void loadSupId(){
        try {
            ObservableList allSupIds= ItemModel.loadSupids();
            cmdSuplierID.setItems(allSupIds);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage());
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage());
        }
    }
}
