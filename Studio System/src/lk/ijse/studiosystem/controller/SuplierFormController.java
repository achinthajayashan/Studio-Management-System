package lk.ijse.studiosystem.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.studiosystem.db.DBConnection;
import lk.ijse.studiosystem.model.CustomerModel;
import lk.ijse.studiosystem.model.SuplierModel;
import lk.ijse.studiosystem.to.Customer;
import lk.ijse.studiosystem.to.Suplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SuplierFormController {
    public TableView tblSuplier;
    public TableColumn colSupId;
    public TableColumn colSupName;
    public TableColumn colSupAddress;
    public TableColumn colSupContact;
    public JFXTextField txtSuplierId;
    public JFXTextField txtSuplierName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;

    public  void initialize(){

        colSupId.setCellValueFactory(new PropertyValueFactory<>("supplier_ID"));
        colSupName.setCellValueFactory(new PropertyValueFactory<>("supplier_name"));
        colSupAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSupContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        //colOption.setCellValueFactory(new PropertyValueFactory<>("option"));
        //colCusEmail.setCellValueFactory(new PropertyValueFactory<>("customerEmail"));

        getAllSupliers();

    }

    public void refreshOnAction(ActionEvent actionEvent) {
    }

    public void addSuplierOnAction(ActionEvent actionEvent) {
        String id = txtSuplierId.getText();
        String name = txtSuplierName.getText();
        String address = txtAddress.getText();
        int contact = Integer.parseInt(txtContact.getText());

        Suplier suplier = new Suplier(id, name, address, contact);
        try {
            boolean isAdded = SuplierModel.save(suplier);

            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Added!").show();
                //CustomerFormController.tblCustomer.refresh();
                getAllSupliers();

//                CustomerFormController customerFormController = new CustomerFormController();
//                customerFormController.getAllCustomers();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateSuplierOnAction(ActionEvent actionEvent) {
        String Id =txtSuplierId.getText();
        String Name =txtSuplierName.getText();
        String Address =txtAddress.getText();
        int Contact = Integer.parseInt(txtContact.getText());

        Suplier suplier = new Suplier(Id,Name,Address,Contact);

        try {
            boolean isUpdated=SuplierModel.update(suplier);
            if (isUpdated) {
                //clear();
//                loadAllCustomers();
                //new Alert(Alert.AlertType.CONFIRMATION, "Update Confirmed!").show();
                new Alert(Alert.AlertType.INFORMATION, "Updated Successfully !").show();
                getAllSupliers();
//                CustomerFormController.tblCustomer.refresh();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }

        } catch (SQLException | ClassNotFoundException ignored) {
            new Alert(Alert.AlertType.ERROR,ignored.getMessage());
        }

    }

    public void deleteSuplierOnAction(ActionEvent actionEvent) {
        String id = txtSuplierId.getText();
        try {
            boolean isDelete =SuplierModel.delete(id);

            if(isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Deleted!").show();
                getAllSupliers();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    public void getAllSupliers(){
        ObservableList data = FXCollections.observableArrayList();
        try {
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM supplier");
            ResultSet allSupliers=statement.executeQuery();


            while (allSupliers.next()){
                //Button btn = new Button("delete");
                data.add(new Suplier(allSupliers.getString(1), allSupliers.getString(2),
                        allSupliers.getString(3), allSupliers.getInt(4)));
            }


        } catch (SQLException e) {
            System.out.println("sql error");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("class not found error");
            throw new RuntimeException(e);
        }
        tblSuplier.setItems(data);
    }

    public void SearchOnAction(MouseEvent mouseEvent) {
        String id = txtSuplierId.getText();
        try {
            Suplier suplier = SuplierModel.search(id);
            if(suplier != null) {
                fillData(suplier);
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage());
            //throw new RuntimeException(e);
        }
    }

    private void fillData(Suplier suplier) {
        txtSuplierId.setText(suplier.getSupplier_ID());
        txtSuplierName.setText(suplier.getSupplier_name());
        txtAddress.setText(suplier.getAddress());
        txtContact.setText(String.valueOf(suplier.getContact()));
    }
}
