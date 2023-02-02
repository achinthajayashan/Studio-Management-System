package lk.ijse.studiosystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.studiosystem.db.DBConnection;
import lk.ijse.studiosystem.to.Customer;
import lk.ijse.studiosystem.util.CrudUtil;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerFormController {
    public  TableView tblCustomer;
    public  TableColumn colCusId;
    public  TableColumn colCusName;
    public TableColumn colCusAddress;
    public  TableColumn colCusContact;
    public TableColumn colCusOption;
    public TableColumn colCusEmail;
    public TableColumn colOption;
    String selectedID;

    public  void initialize(){

        colCusId.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        colCusName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colCusAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        colCusContact.setCellValueFactory(new PropertyValueFactory<>("customerContact"));
        //colOption.setCellValueFactory(new PropertyValueFactory<>("option"));
        colCusEmail.setCellValueFactory(new PropertyValueFactory<>("customerEmail"));

        getAllCustomers();

    }

    public void searchOnAction(KeyEvent keyEvent) {
    }

    public void addCustomerFormOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage2 = new Stage();
        stage2.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/studiosystem/view/AddCustomerForm.fxml"))));
        //stage2.centerOnScreen();
        //stage2.setFullScreen(true);
        stage2.initStyle(StageStyle.UNDECORATED);
        stage2.setX(500);
        stage2.setY(220);


        stage2.show();
        getAllCustomers();
        tblCustomer.refresh();
    }

    public void getAllCustomers(){
        ObservableList data = FXCollections.observableArrayList();
        try {
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM customer");
            ResultSet allCustomers=statement.executeQuery();


            while (allCustomers.next()){
                Button btn = new Button("delete");
                data.add(new Customer(allCustomers.getString(1), allCustomers.getString(2),
                        allCustomers.getString(3), allCustomers.getString(4), allCustomers.getString(5)));
            }


        } catch (SQLException e) {
            System.out.println("sql error");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("class not found error");
            throw new RuntimeException(e);
        }
        tblCustomer.setItems(data);
    }


    public void refreshOnAction(ActionEvent actionEvent) {
        getAllCustomers();
    }

    public void updateCustomerFormOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage3 = new Stage();
        stage3.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/studiosystem/view/UpdateCustomerForm.fxml"))));
        //stage2.centerOnScreen();
        //stage2.setFullScreen(true);
        stage3.initStyle(StageStyle.UNDECORATED);
        stage3.setX(1080);
        stage3.setY(220);


        stage3.show();
        tblCustomer.refresh();

    }

    public void searchaCustomerOnAction(ActionEvent actionEvent) {
    }

    public void deleteSelectedCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
    }
}
