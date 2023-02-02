package lk.ijse.studiosystem.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.studiosystem.model.BookingModel;
import lk.ijse.studiosystem.model.CustomerModel;
import lk.ijse.studiosystem.model.ItemModel;
import lk.ijse.studiosystem.to.Customer;
import lk.ijse.studiosystem.to.Item;

import java.sql.SQLException;

public class AddItemFormController {
    public JFXTextField txtItemId;
    public JFXTextField txtItemName;
    public JFXTextField txtUnitPrice;
    public AnchorPane ItemAddContext;
    public JFXTextField txtQtyOnHnd;
    public JFXComboBox cmbSuplierId;

    public void initialize(){
        loadSupId();
    }

    public void addOnAction(ActionEvent actionEvent) {
        String id = txtItemId.getText();
        String name = txtItemName.getText();
        Double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qty_on_hnd = Integer.parseInt(txtQtyOnHnd.getText());
        String supplier_ID = String.valueOf(cmbSuplierId.getValue());
//        String contact = txtCusContact.getText();
//        String cus_email = txtCusEmail.getText();

        Item item = new Item(id, name, unitPrice,qty_on_hnd,supplier_ID);
        try {
            boolean isAdded = ItemModel.save(item);

            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Added!").show();
                //CustomerFormController.tblCustomer.refresh();
                closeStage(ItemAddContext);

//                CustomerFormController customerFormController = new CustomerFormController();
//                customerFormController.getAllCustomers();
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

    public void loadSupId(){
        try {
            ObservableList allSupIds= ItemModel.loadSupids();
            cmbSuplierId.setItems(allSupIds);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage());
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage());
        }
    }
}
