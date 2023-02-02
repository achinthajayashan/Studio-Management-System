package lk.ijse.studiosystem.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.studiosystem.model.CustomerModel;
import lk.ijse.studiosystem.to.Customer;
import lk.ijse.studiosystem.util.CrudUtil;

import java.sql.SQLException;

public class UpdateCustomerForm {

    public AnchorPane CustomerUpdateContext;
    public JFXTextField txtCusId;
    public JFXTextField txtCusName;
    public JFXTextField txtCusAddress;
    public JFXTextField txtCusContact;
    public JFXTextField txtCusEmail;

    public void loadCustomerDetailsOnAction(ActionEvent actionEvent) {
        String id = txtCusId.getText();
        try {
            Customer customer = CustomerModel.search(id);
            if(customer != null) {
                fillData(customer);
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage());
            throw new RuntimeException(e);
        }
    }
    public void updateOnAction(ActionEvent actionEvent) {
        String cusId =txtCusId.getText();
        String cusName =txtCusName.getText();
        String cusAddress = txtCusAddress.getText();
        String cusContact =txtCusContact.getText();
        String cusEmail = txtCusEmail.getText();

        Customer customer = new Customer(cusId,cusName,cusAddress,cusContact,cusEmail);

        try {
            boolean isUpdated=CustomerModel.update(customer);
            if (isUpdated) {
                //clear();
//                loadAllCustomers();
                //new Alert(Alert.AlertType.CONFIRMATION, "Update Confirmed!").show();
                new Alert(Alert.AlertType.INFORMATION, "Updated Successfully !").show();
//                CustomerFormController.tblCustomer.refresh();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }

        } catch (SQLException | ClassNotFoundException ignored) {}
        //TableContextFull.refresh();

        closeStage(CustomerUpdateContext);
//        CustomerFormController customerFormController = new CustomerFormController();
//        customerFormController.getAllCustomers();
    }

    private void closeStage(AnchorPane anchorPane){
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
    }

    private void fillData(Customer customer) {
        txtCusId.setText(customer.getCustomerID());
        txtCusName.setText(customer.getCustomerName());
        txtCusAddress.setText(customer.getCustomerAddress());
        txtCusContact.setText(customer.getCustomerContact());
        txtCusEmail.setText(customer.getCustomerEmail());
    }


    public void deleteOnAction(ActionEvent actionEvent) {
        String id = txtCusId.getText();
        try {
            boolean isDelete = CustomerModel.delete(id);

            if(isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Delete!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
