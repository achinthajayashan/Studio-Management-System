package lk.ijse.studiosystem.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static AnchorPane pane;

    public static void navigate(Routes route , AnchorPane pane) throws IOException {
        Navigation.pane=pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage) Navigation.pane.getScene().getWindow();

        switch (route){
            case DASHBOARD:
                initUI("DashboardForm.fxml");
                break;
            case CUSTOMER:
                initUI("CustomerForm.fxml");
                break;
            case ITEM:
                initUI("ItemForm.fxml");
                break;
            case ORDER:
                initUI("PlaceOrderForm.fxml");
                break;
            case EMPLOYEE:
                initUI("EmployeeForm.fxml");
                break;
            case SUPLIER:
                initUI("Suplierform.fxml");
                break;
            case PACKAGES:
                initUI("Packageform.fxml");
                break;
            case BOOKING:
                initUI("BookingForm.fxml");
                break;
            case SALARYPAYMENT:
                initUI("SalaryForm.fxml");
                break;
            case REPORT:
                initUI("Reportsform.fxml");
                break;
            case CASHDEPOSIT:
                initUI("PaymentForm.fxml");
                break;
            default:
                new Alert(Alert.AlertType.ERROR,"No UI Found");
        }
    }

    private static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class
                .getResource("/lk/ijse/studiosystem/view/" + location)));
    }
}

