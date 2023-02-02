package lk.ijse.studiosystem.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.studiosystem.model.CustomerModel;
import lk.ijse.studiosystem.model.EmployeeModel;
import lk.ijse.studiosystem.to.Customer;
import lk.ijse.studiosystem.to.Employee;

import java.sql.SQLException;

public class AddEmployeeFormController {
    public AnchorPane EmployeeAddContext;
    public JFXTextField txtEmpId;
    public JFXTextField txtEmpName;
    public JFXTextField txtEmpNic;
    public JFXTextField txtEmpDob;
    public JFXTextField txtEmpContact;
    public JFXTextField txtEmpEmail;

    public void addOnAction(ActionEvent actionEvent) {
        String id = txtEmpId.getText();
        String name = txtEmpName.getText();
        String nic = txtEmpNic.getText();
        String dob = txtEmpDob.getText();
        int contact = Integer.parseInt(txtEmpContact.getText());
        String email = txtEmpEmail.getText();

        Employee employee = new Employee(id, name, nic, dob,contact,email);
        try {
            boolean isAdded = EmployeeModel.save(employee);

            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Added!").show();
                //CustomerFormController.tblCustomer.refresh();
                closeStage(EmployeeAddContext);

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
}
