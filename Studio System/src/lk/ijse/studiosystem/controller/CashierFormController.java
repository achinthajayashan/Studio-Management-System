package lk.ijse.studiosystem.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.studiosystem.model.DashboardPaneModel;
import lk.ijse.studiosystem.util.Navigation;
import lk.ijse.studiosystem.util.Routes;
import org.apache.poi.hssf.record.formula.functions.Na;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CashierFormController {
    public Label lblTime;
    public AnchorPane mainContext;
    @FXML
    private Label lblCusCount;

    @FXML
    private Label lblItemCount;

    @FXML
    private Label lblOrderCount;

    @FXML
    private Label lblSuplierCount;


    public void initialize(){
        setDateAndTime();
        getCustomerCount();
        getItemsCount();
        getOrdersCount();
        getSupliersCount();
    }

    private void setDateAndTime() {
        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e->{
            DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd   HH:mm:ss");
            lblTime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void viewDashboardOnAction(ActionEvent actionEvent) {
    }

    public void viewCustomerFormOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CUSTOMER,mainContext);
    }

    public void viewItemFormOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ITEM,mainContext);
    }

    public void viewOrderFormOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ORDER,mainContext);
    }

    public void viewPackagesFormOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PACKAGES,mainContext);
    }

    public void viewSupliersFormOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SUPLIER,mainContext);
    }

    public void viewStocksFormOnAction(ActionEvent actionEvent) {
    }

    public void viewCamersFormOnAction(ActionEvent actionEvent) {
    }

    public void viewEmployeesFormOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.EMPLOYEE,mainContext);
    }

    public void viewSalaryPaymentsFormOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SALARYPAYMENT,mainContext);

    }

    public void viewCashDepositsFormOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CASHDEPOSIT,mainContext);
    }

    public void viewReportsFormOnAction(ActionEvent actionEvent) {
    }

    public void viewBookingsFormOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.BOOKING,mainContext);
    }

    public void signOutOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void getCustomerCount(){
        try {
            int cusCount= DashboardPaneModel.getCusCount();
            lblCusCount.setText(String.valueOf(cusCount));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void getItemsCount(){
        try {
            int cusCount= DashboardPaneModel.getItemCount();
            lblItemCount.setText(String.valueOf(cusCount));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void getOrdersCount(){
        try {
            int cusCount= DashboardPaneModel.getOrderCount();
            lblOrderCount.setText(String.valueOf(cusCount));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void getSupliersCount(){
        try {
            int cusCount= DashboardPaneModel.getSupplierCount();
            lblSuplierCount.setText(String.valueOf(cusCount));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
