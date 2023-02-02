package lk.ijse.studiosystem.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.studiosystem.db.DBConnection;
import lk.ijse.studiosystem.model.BookingModel;
import lk.ijse.studiosystem.model.SalaryModel;
import lk.ijse.studiosystem.model.SuplierModel;
import lk.ijse.studiosystem.to.Salary;
import lk.ijse.studiosystem.to.Suplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SalaryFormController {

    @FXML
    private TableView tblSalary;

    @FXML
    private TableColumn colSalaryId;

    @FXML
    private TableColumn colMonth;

    @FXML
    private TableColumn colValue;

    @FXML
    private TableColumn colSupContact;

    @FXML
    private JFXTextField txtSalaryId;

    @FXML
    private JFXTextField txtMonth;

    @FXML
    private JFXTextField txtValue;

    @FXML
    private JFXComboBox cmbEmployeeId;

    public  void initialize(){

        colSalaryId.setCellValueFactory(new PropertyValueFactory<>("salary_id"));
        colMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        colValue.setCellValueFactory(new PropertyValueFactory<>("value"));
        colSupContact.setCellValueFactory(new PropertyValueFactory<>("emp_id"));
        //colOption.setCellValueFactory(new PropertyValueFactory<>("option"));
        //colCusEmail.setCellValueFactory(new PropertyValueFactory<>("customerEmail"));

        getAllSalary();
        loadEmpId();

    }

    @FXML
    void SearchOnAction(MouseEvent event) {
        String id = txtSalaryId.getText();
        try {
            Salary salary = SalaryModel.search(id);
            if(salary != null) {
                fillData(salary);
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage());
            //throw new RuntimeException(e);
        }

    }

    private void fillData(Salary salary) {
        txtSalaryId.setText(salary.getSalary_id());
        txtMonth.setText(salary.getMonth());
        txtValue.setText(String.valueOf(salary.getValue()));
        cmbEmployeeId.setValue(String.valueOf(salary.getEmp_id()));
    }

    @FXML
    void addSuplierOnAction(ActionEvent event) {
        String id = txtSalaryId.getText();
        String month = txtMonth.getText();
        double value = Double.parseDouble(txtValue.getText());
        String emp_id = String.valueOf(cmbEmployeeId.getValue());

        Salary salary = new Salary(id, month, value, emp_id);
        try {
            boolean isAdded = SalaryModel.save(salary);

            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Salary recorded!").show();
                //CustomerFormController.tblCustomer.refresh();
                getAllSalary();

//                CustomerFormController customerFormController = new CustomerFormController();
//                customerFormController.getAllCustomers();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }

    }

    @FXML
    void deleteSuplierOnAction(ActionEvent event) {
        String id = txtSalaryId.getText();
        try {
            boolean isDelete =SalaryModel.delete(id);

            if(isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Salary record Deleted!").show();
                getAllSalary();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    @FXML
    void refreshOnAction(ActionEvent event) {

    }

    @FXML
    void updateSuplierOnAction(ActionEvent event) {
        String id = txtSalaryId.getText();
        String month = txtMonth.getText();
        double value = Double.parseDouble(txtValue.getText());
        String emp_id = String.valueOf(cmbEmployeeId.getValue());

        Salary salary = new Salary(id, month, value, emp_id);
        try {
            boolean isAdded = SalaryModel.update(salary);

            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Salary record updated!").show();
                //CustomerFormController.tblCustomer.refresh();
                getAllSalary();

//                CustomerFormController customerFormController = new CustomerFormController();
//                customerFormController.getAllCustomers();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }

    }
    public void getAllSalary(){
        ObservableList data = FXCollections.observableArrayList();
        try {
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM salary");
            ResultSet salaries=statement.executeQuery();


            while (salaries.next()){
                //Button btn = new Button("delete");
                data.add(new Salary(salaries.getString(1), salaries.getString(2),
                        salaries.getDouble(3), salaries.getString(4)));
            }


        } catch (SQLException e) {
            System.out.println("sql error");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("class not found error");
            throw new RuntimeException(e);
        }
        tblSalary.setItems(data);
    }

    public void loadEmpId(){
        try {
            ObservableList allCusIds= SalaryModel.loadEmpsid();
            cmbEmployeeId.setItems(allCusIds);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage());
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage());
        }
    }

}

