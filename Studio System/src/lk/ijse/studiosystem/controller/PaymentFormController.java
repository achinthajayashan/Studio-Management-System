package lk.ijse.studiosystem.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import lk.ijse.studiosystem.db.DBConnection;
import lk.ijse.studiosystem.model.*;
import lk.ijse.studiosystem.to.Packages;
import lk.ijse.studiosystem.to.Payment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class PaymentFormController {

    @FXML
    private TableView tblPayment;

    @FXML
    private TableColumn colPaymentId;

    @FXML
    private TableColumn colValue;

    @FXML
    private TableColumn colDate;

    @FXML
    private TableColumn colPaymentType;

    @FXML
    private TableColumn colOrderId;

    @FXML
    private TableColumn colBookingId;

    @FXML
    private JFXTextField txtPaymentId;

    @FXML
    private JFXTextField txtValue;

    @FXML
    private JFXDatePicker txtDate;

    @FXML
    private JFXComboBox cmbOrderID;

    @FXML
    private JFXComboBox cmbBookingId;

    @FXML
    private JFXComboBox cmbPaymentType;

    @FXML
    private JFXTextField txtType;

    public void initialize(){
        loadCusId();
        getAllPayments();
    }

    @FXML
    void SearchOnAction(MouseEvent event) {

    }

    @FXML
    void addOnAction(ActionEvent event){
        String id = txtPaymentId.getText();
        String orderId = String.valueOf(cmbOrderID.getValue());
        String bookingId= String.valueOf(cmbBookingId.getValue());
        double value = Double.parseDouble(txtValue.getText());
        String type = txtType.getText();
        LocalDate date= txtDate.getValue();

        Payment payment = new Payment(id, value, date,type,orderId,bookingId);
        try {
            boolean isAdded = PaymentModel.save(payment);

            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Payment Added!").show();
                //CustomerFormController.tblCustomer.refresh();
                getAllPayments();

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
    void deleteOnAction(ActionEvent event) {

    }

    @FXML
    void refreshOnAction(ActionEvent event) {

    }

    @FXML
    void updateOnAction(ActionEvent event) {

    }

    public void loadCusId(){
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> idList = OrderModel.loadOrderIds();

            for (String id : idList) {
                observableList.add(id);
            }
            cmbOrderID.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void getAllPayments(){
        ObservableList data = FXCollections.observableArrayList();
        try {
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM payment");
            ResultSet allPayments=statement.executeQuery();


            while (allPayments.next()){
                //Button btn = new Button("delete");
                data.add(new Payment(allPayments.getString(1), allPayments.getDouble(2),
                        String.valueOf(allPayments.getDate(3)),allPayments.getString(4),allPayments.getString(5),allPayments.getString(6)));
            }


        } catch (SQLException e) {
            System.out.println("sql error");

        } catch (ClassNotFoundException e) {
            System.out.println("class not found error");

        }
        tblPayment.setItems(data);
    }
    }


