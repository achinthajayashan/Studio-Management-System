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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.studiosystem.db.DBConnection;
import lk.ijse.studiosystem.model.BookingModel;
import lk.ijse.studiosystem.model.PackageModel;
import lk.ijse.studiosystem.to.Booking;
import lk.ijse.studiosystem.to.Packages;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class BookingFormController {

    @FXML
    private TableView tblBooking;

    @FXML
    private TableColumn colBookingId;

    @FXML
    private TableColumn colBooking;

    @FXML
    private TableColumn colDate;

    @FXML
    private TableColumn colCusId;

    @FXML
    private TableColumn colPackageId;

    @FXML
    private JFXTextField txtBookingId;

    @FXML
    private JFXTextField txtLocation;

    @FXML
    private JFXDatePicker txtDate;

    @FXML
    private JFXComboBox cmbCustomerID;

    @FXML
    private JFXComboBox cmbPkgId;

    public  void initialize(){

        colBookingId.setCellValueFactory(new PropertyValueFactory<>("booking_id"));
        colBooking.setCellValueFactory(new PropertyValueFactory<>("location"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colCusId.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        colPackageId.setCellValueFactory(new PropertyValueFactory<>("package_id"));

        loadCusId();
        loadPkgId();
        getAllBookings();

    }
    @FXML
    void SearchOnAction(MouseEvent event) {
        String id = txtBookingId.getText();
        try {
            Booking booking =BookingModel.search(id);
            if(booking != null) {
                fillData(booking);
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage());
            //throw new RuntimeException(e);
        }


    }

    private void fillData(Booking booking) {
        txtBookingId.setText(booking.getBooking_id());
        txtLocation.setText(booking.getLocation());
        txtDate.setValue(LocalDate.parse(booking.getDate()));
        cmbCustomerID.setValue(booking.getCustomer_id());
        cmbPkgId.setValue(booking.getPackage_id());
    }

    @FXML
    void addOnAction(ActionEvent event) {
        String id = txtBookingId.getText();
        String location = txtLocation.getText();
        String date = String.valueOf(txtDate.getValue());
        String cus_id= String.valueOf(cmbCustomerID.getValue());
        String pkg_id= String.valueOf(cmbPkgId.getValue());

        Booking booking= new Booking(id,location,date,cus_id,pkg_id);
        try {
            boolean isAdded =BookingModel.save(booking);

            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "New Booking Added!").show();
                //CustomerFormController.tblCustomer.refresh();
                getAllBookings();

//                CustomerFormController customerFormController = new CustomerFormController();
//                customerFormController.getAllCustomers();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        String id = txtBookingId.getText();
        try {
            boolean isDelete =BookingModel.delete(id);

            if(isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Booking Deleted!").show();
                getAllBookings();
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
    void updateOnAction(ActionEvent event) {
        String id = txtBookingId.getText();
        String location = txtLocation.getText();
        String date = String.valueOf(txtDate.getValue());
        String cus_id= String.valueOf(cmbCustomerID.getValue());
        String pkg_id= String.valueOf(cmbPkgId.getValue());

        Booking booking= new Booking(id,location,date,cus_id,pkg_id);
        try {
            boolean isAdded =BookingModel.update(booking);

            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Booking updated!").show();
                //CustomerFormController.tblCustomer.refresh();
                getAllBookings();

//                CustomerFormController customerFormController = new CustomerFormController();
//                customerFormController.getAllCustomers();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }

    }

    private void getAllBookings() {
        ObservableList data = FXCollections.observableArrayList();
        try {
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM book");
            ResultSet allBookings=statement.executeQuery();


            while (allBookings.next()){
                //Button btn = new Button("delete");
                data.add(new Booking(allBookings.getString(1), allBookings.getString(2),
                        allBookings.getString(3),allBookings.getString(4), allBookings.getString(5)));
            }


        } catch (SQLException e) {
            System.out.println("sql error");

        } catch (ClassNotFoundException e) {
            System.out.println("class not found error");

        }
        tblBooking.setItems(data);
    }
    public void loadCusId(){
        try {
            ObservableList allCusIds= BookingModel.loadCustid();
            cmbCustomerID.setItems(allCusIds);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage());
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    public void loadPkgId(){
        try {
            ObservableList allPkgIds= BookingModel.loadpkgsid();
            cmbPkgId.setItems(allPkgIds);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage());
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage());
        }
    }
}

