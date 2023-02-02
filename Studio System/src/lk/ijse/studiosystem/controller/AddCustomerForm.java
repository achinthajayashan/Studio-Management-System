package lk.ijse.studiosystem.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import lk.ijse.studiosystem.model.CustomerModel;
import lk.ijse.studiosystem.to.Customer;

import javax.swing.*;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddCustomerForm {
    public JFXTextField txtCusId;
    public JFXTextField txtCusName;
    public JFXTextField txtCusAddress;
    public JFXTextField txtCusContact;
    public AnchorPane CustomerAddContext;
    public JFXTextField txtCusEmail;

    @FXML
    private Label txtRegexContact;

    public void addOnAction(ActionEvent actionEvent) throws InterruptedException {
        String id = txtCusId.getText();
        String name = txtCusName.getText();
        String address = txtCusAddress.getText();
        String contact = txtCusContact.getText();
        String cus_email = txtCusEmail.getText();

        Customer customer = new Customer(id, name, address, contact,cus_email);
        try {
            boolean isAdded = CustomerModel.save(customer);

            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Added!").show();
                //CustomerFormController.tblCustomer.refresh();
                closeStage(CustomerAddContext);

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

    public void contactKeyPress(javafx.scene.input.KeyEvent keyEvent) {
    }

//    public void keyPressedContact(KeyEvent keyEvent) {
//        Pattern contactPattern = Pattern.compile("^(07)([0-9]{8})$");
//        Matcher matcher = contactPattern.matcher(txtCusContact.getText());
//
//        boolean isMatch =matcher.matches();
//
//        if (!isMatch) {
//            txtCusContact.setStyle("-fx-text-box-border: #d63031; -fx-focus-color: #d63031;");
//
//            Alert alert = new Alert(Alert.AlertType.ERROR);// line 1
//        } else {
//            txtCusContact.setStyle("-fx-text-box-border: #ffa300; -fx-focus-color:#ffa300;");
//        }
//    }

    public void keyReleasedContact(KeyEvent keyEvent) {
        Pattern contactPattern = Pattern.compile("^(07)([0-9]{8})$");
        Matcher matcher = contactPattern.matcher(txtCusContact.getText());

        boolean isMatch =matcher.matches();

        if (!isMatch) {
            txtCusContact.setFocusColor(Paint.valueOf("red"));
            System.out.println("invalid");

            //Alert alert = new Alert(Alert.AlertType.ERROR);// line 1
        } else {
            txtCusContact.setFocusColor(Paint.valueOf("green"));
        }
    }

    public void keyPressOnEmai(KeyEvent keyEvent) {

        Pattern contactPattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher matcher = contactPattern.matcher(txtCusEmail.getText());

        boolean isMatch =matcher.matches();

        if (!isMatch) {
            txtCusEmail.setFocusColor(Paint.valueOf("red"));
            System.out.println("invalid");

            //Alert alert = new Alert(Alert.AlertType.ERROR);// line 1
        } else {
           txtCusEmail.setFocusColor(Paint.valueOf("green"));
        }
    }
}
