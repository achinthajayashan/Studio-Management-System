package lk.ijse.studiosystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.studiosystem.db.DBConnection;
import lk.ijse.studiosystem.to.Customer;
import lk.ijse.studiosystem.to.Employee;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeFormController {
    public TableView tblEmployee;
    public TableColumn colEmpId;
    public TableColumn colEmpName;
    public TableColumn colEmpNic;
    public TableColumn colEmpDob;
    public TableColumn colEmpContact;
    public TableColumn colEmpEmail;

    public  void initialize(){

        colEmpId.setCellValueFactory(new PropertyValueFactory<>("empID"));
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("empName"));
        colEmpNic.setCellValueFactory(new PropertyValueFactory<>("empNic"));
        colEmpDob.setCellValueFactory(new PropertyValueFactory<>("empDob"));
        colEmpContact.setCellValueFactory(new PropertyValueFactory<>("empContact"));
        colEmpEmail.setCellValueFactory(new PropertyValueFactory<>("empEmail"));

        getAllEmployees();

    }

    public void addEmployeeFormOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage6 = new Stage();
        stage6.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/studiosystem/view/AddEmployeeForm.fxml"))));
        //stage2.centerOnScreen();
        //stage2.setFullScreen(true);
        stage6.initStyle(StageStyle.UNDECORATED);
        stage6.setX(500);
        stage6.setY(220);


        stage6.show();
        getAllEmployees();
        tblEmployee.refresh();
    }

    public void getAllEmployees(){
        ObservableList data = FXCollections.observableArrayList();
        try {
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM employee");
            ResultSet allEmployees=statement.executeQuery();


            while (allEmployees.next()){
                //Button btn = new Button("delete");
                data.add(new Employee(allEmployees.getString(1), allEmployees.getString(2), allEmployees.getString(3),
                        allEmployees.getString(4),allEmployees.getInt(5), allEmployees.getString(6) ));
            }


        } catch (SQLException e) {
            System.out.println("sql error");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("class not found error");
            throw new RuntimeException(e);
        }
        tblEmployee.setItems(data);
    }

    public void refreshOnAction(ActionEvent actionEvent) {
        getAllEmployees();
    }

    public void updateEmployeeFormOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage7 = new Stage();
        stage7.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/studiosystem/view/UpdateEmployeeForm.fxml"))));
        //stage2.centerOnScreen();
        //stage2.setFullScreen(true);
        stage7.initStyle(StageStyle.UNDECORATED);
        stage7.setX(1080);
        stage7.setY(220);


        stage7.show();
        tblEmployee.refresh();

    }

    public void searchEmployeeOnAction(ActionEvent actionEvent) {
    }
}
