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

public class UpdateEmployeeFormController {

    public JFXTextField txtEmpEmail;
    public JFXTextField txxtEmpContact;
    public JFXTextField txtEmpDob;
    public JFXTextField txtEmpNic;
    public JFXTextField txtEmpName;
    public JFXTextField txtEmpId;
    public AnchorPane EmployeeUpdateContext;

    public void loadEmployeeDetailsOnAction(ActionEvent actionEvent) {
        String id = txtEmpId.getText();
        try {
            Employee employee = EmployeeModel.search(id);
            if(employee != null) {
                fillData(employee);
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        String empId =txtEmpId.getText();
        String empName =txtEmpName.getText();
        String empNic = txtEmpNic.getText();
        String empDob =txtEmpDob.getText();
        int empContact = Integer.parseInt(txxtEmpContact.getText());
        String empEmail=txtEmpEmail.getText();

        Employee employee = new Employee(empId,empName,empNic,empDob,empContact,empEmail);

        try {
            boolean isUpdated=EmployeeModel.update(employee);
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

        closeStage(EmployeeUpdateContext);
    }

    private void closeStage(AnchorPane anchorPane){
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
    }

    private void fillData(Employee employee) {
        txtEmpId.setText(employee.getEmpID());
        txtEmpName.setText(employee.getEmpName());
        txtEmpNic.setText(employee.getEmpNic());
        txtEmpDob.setText(employee.getEmpDob());
        txxtEmpContact.setText(String.valueOf(employee.getEmpContact()));
        txtEmpEmail.setText(employee.getEmpEmail());
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String id = txtEmpId.getText();
        try {
            boolean isDelete = EmployeeModel.delete(id);

            if(isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Deleted!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
