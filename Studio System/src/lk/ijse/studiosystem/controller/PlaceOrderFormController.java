package lk.ijse.studiosystem.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import lk.ijse.studiosystem.model.CustomerModel;
import lk.ijse.studiosystem.model.ItemModel;
import lk.ijse.studiosystem.model.OrderModel;
import lk.ijse.studiosystem.model.PlaceOrderModel;
import lk.ijse.studiosystem.to.*;
import lk.ijse.studiosystem.view.TM.PlaceOrderTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlaceOrderFormController {

    public TableView tblBooking;
    public TableColumn colItemId;
    public TableColumn colDescription;
    public TableColumn colQuantity;
    public TableColumn colUnitPrice;
    public TableColumn colPackageId;
    public TableColumn colAction;
    public Label lblOrderId;
    public JFXComboBox cmbCustomerID;
    public Label lblItemId1;
    public JFXComboBox cmbItemId;
    public JFXTextField txtCustomerName;
    public JFXTextField txtDescription;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtQty;
    public Label lblTotal;

    private ObservableList<PlaceOrderTM> obList = FXCollections.observableArrayList();

    public void initialize() {
        loadNextOrderId();
        loadCustomerIds();
        loadItemCodes();
        setCellValueFactory();
    }

    public void addToCartOnAction(ActionEvent actionEvent) {
        String code = String.valueOf(cmbItemId.getValue());
        int qty = Integer.parseInt(txtQty.getText());
        String desc = txtDescription.getText();
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        double total = unitPrice * qty;
        Button btnDelete = new Button("Delete");

        txtQty.setText(null);

        if (!obList.isEmpty()) {
            L1:
            /* check same item has been in table. If so, update that row instead of adding new row to the table */
            for (int i = 0; i < tblBooking.getItems().size(); i++) {
                if (colItemId.getCellData(i).equals(code)) {
                    qty += (int) colQuantity.getCellData(i);
                    total = unitPrice * qty;

                    obList.get(i).setQty(qty);
                    obList.get(i).setTotal(total);


                    tblBooking.refresh();
                    return;
                }
            }
        }

        /* set delete button to some action before it put on obList */
        btnDelete.setOnAction((e) -> {
            ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?", ok, no);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.orElse(no) == ok) {
                PlaceOrderTM tm = (PlaceOrderTM) tblBooking.getSelectionModel().getSelectedItem();
                /*
                netTot = Double.parseDouble(txtNetTot.getText());
                netTot = netTot - tm.getTotalPrice();
                */

                tblBooking.getItems().removeAll(tblBooking.getSelectionModel().getSelectedItem());
                tblBooking.refresh();
            }
        });
        obList.add(new PlaceOrderTM(code, desc, qty, unitPrice, total, btnDelete));
        tblBooking.setItems(obList);
        tblBooking.refresh();

    }

    public void placeOrderOnAction(ActionEvent actionEvent) {
        String orderId = lblOrderId.getText();
        String customerId = String.valueOf(cmbCustomerID.getValue());

        ArrayList<CartDetail> cartDetails = new ArrayList<>();

        /* load all cart items' to orderDetails arrayList */
        for (int i = 0; i < tblBooking.getItems().size(); i++) {
            /* get each row details to (PlaceOrderTm)tm in each time and add them to the orderDetails */
            PlaceOrderTM tm = obList.get(i);
            cartDetails.add(new CartDetail(orderId, tm.getItemCode(), tm.getQty(), tm.getDescription(), tm.getUnitPrice()));
            System.out.println(orderId);
        }

        PlaceOrder placeOrder = new PlaceOrder(customerId, orderId, cartDetails);
        try {
            boolean isPlaced = PlaceOrderModel.placeOrder(placeOrder);
            if (isPlaced) {
                new Alert(Alert.AlertType.INFORMATION, "Order Placed!").show();
                /* to clear table */
                obList.clear();
                loadNextOrderId();

            } else {
                new Alert(Alert.AlertType.ERROR, "Order Not Placed!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            //throw new RuntimeException(e);
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }
    }

    private void loadNextOrderId() {
        try {
            String orderId = OrderModel.generateNextOrderId();
            lblOrderId.setText(orderId);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadCustomerIds() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> idList = CustomerModel.loadCustomerIds();

            for (String id : idList) {
                observableList.add(id);
            }
            cmbCustomerID.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadItemCodes() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> itemList = ItemModel.loadItemCodes();

            for (String code : itemList) {
                observableList.add(code);
            }
            cmbItemId.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillItemFields(Item item) {
        txtDescription.setText(item.getItem_name());
        txtUnitPrice.setText(String.valueOf(item.getUnit_price()));
        txtQtyOnHand.setText(String.valueOf(item.getQty_on_hnd()));
    }

    private void fillCustomerFields(Customer customer){
        txtCustomerName.setText(customer.getCustomerName());
    }

    public void loadItemCodesOnAction(ActionEvent actionEvent) {
        String code = String.valueOf(cmbItemId.getValue());
        try {
            Item item = ItemModel.search(code);
            fillItemFields(item);
            txtQty.requestFocus();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadCustomerDetailsOnAction(ActionEvent actionEvent) {
        String code = String.valueOf(cmbCustomerID.getValue());
        try {
            Customer customer = CustomerModel.search(code);
            fillCustomerFields(customer);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colItemId.setCellValueFactory(new PropertyValueFactory("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory("description"));
        colQuantity.setCellValueFactory(new PropertyValueFactory("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colPackageId.setCellValueFactory(new PropertyValueFactory("total"));
        colAction.setCellValueFactory(new PropertyValueFactory("delete"));
    }


    public void onKeyPressedQty(KeyEvent keyEvent) {
        Pattern contactPattern = Pattern.compile("^[0-9]{1,3}$");
        Matcher matcher = contactPattern.matcher(txtQty.getText());

        boolean isMatch =matcher.matches();

        if (!isMatch) {
            txtQty.setFocusColor(Paint.valueOf("red"));
            System.out.println("invalid");

            //Alert alert = new Alert(Alert.AlertType.ERROR);// line 1
        } else {
            txtQty.setFocusColor(Paint.valueOf("green"));
        }
    }
}


