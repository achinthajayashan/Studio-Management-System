package lk.ijse.studiosystem.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.studiosystem.db.DBConnection;
import lk.ijse.studiosystem.model.PackageModel;
import lk.ijse.studiosystem.model.SuplierModel;
import lk.ijse.studiosystem.to.Packages;
import lk.ijse.studiosystem.to.Suplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PackageFormController {

    public TableView tblPkg;
    public TableColumn colPkgId;
    public TableColumn colPkgDesc;
    public TableColumn colPkgPrice;
    public JFXTextField txtPkgId;
    public JFXTextField txtPkgPrice;
    public JFXTextField pkgDescription;

    public  void initialize(){

        colPkgId.setCellValueFactory(new PropertyValueFactory<>("package_ID"));
        colPkgDesc.setCellValueFactory(new PropertyValueFactory<>("package_name"));
        colPkgPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        getAllPackages();

    }

    private void getAllPackages() {
        ObservableList data = FXCollections.observableArrayList();
        try {
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM package");
            ResultSet allPackages=statement.executeQuery();


            while (allPackages.next()){
                //Button btn = new Button("delete");
                data.add(new Packages(allPackages.getString(1), allPackages.getString(2),
                        allPackages.getDouble(3)));
            }


        } catch (SQLException e) {
            System.out.println("sql error");

        } catch (ClassNotFoundException e) {
            System.out.println("class not found error");

        }
        tblPkg.setItems(data);
    }

    public void refreshOnAction(ActionEvent actionEvent) {
    }

    public void addOnAction(ActionEvent actionEvent) {
        String id = txtPkgId.getText();
        String name = pkgDescription.getText();
        double price = Double.parseDouble(txtPkgPrice.getText());

        Packages packages= new Packages(id, name, price);
        try {
            boolean isAdded = PackageModel.save(packages);

            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "New Package Added!").show();
                //CustomerFormController.tblCustomer.refresh();
                getAllPackages();

//                CustomerFormController customerFormController = new CustomerFormController();
//                customerFormController.getAllCustomers();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        String id = txtPkgId.getText();
        String name = pkgDescription.getText();
        double price = Double.parseDouble(txtPkgPrice.getText());

        Packages packages= new Packages(id, name, price);
        try {
            boolean isUpdated=PackageModel.update(packages);
            if (isUpdated) {
                //clear();
//                loadAllCustomers();
                //new Alert(Alert.AlertType.CONFIRMATION, "Update Confirmed!").show();
                new Alert(Alert.AlertType.INFORMATION, "Updated Successfully !").show();
                getAllPackages();
//                CustomerFormController.tblCustomer.refresh();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }

        } catch (SQLException | ClassNotFoundException ignored) {
            new Alert(Alert.AlertType.ERROR,ignored.getMessage());
        }

    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String id = txtPkgId.getText();
        try {
            boolean isDelete =PackageModel.delete(id);

            if(isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Package Deleted!").show();
                getAllPackages();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    public void SearchOnAction(MouseEvent mouseEvent) {
        String id = txtPkgId.getText();
        try {
            Packages packages =PackageModel.search(id);
            if(packages != null) {
                fillData(packages);
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage());
            //throw new RuntimeException(e);
        }
    }

    private void fillData(Packages packages) {
        txtPkgId.setText(packages.getPackage_ID());
        pkgDescription.setText(packages.getPackage_name());
        txtPkgPrice.setText(String.valueOf(packages.getPrice()));
    }
}
