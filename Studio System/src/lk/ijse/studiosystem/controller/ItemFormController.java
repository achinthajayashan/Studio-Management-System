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
import lk.ijse.studiosystem.to.Item;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemFormController {
    public TableView tblItem;
    public TableColumn colItemId;
    public TableColumn colItemName;
    public TableColumn colItemUnitPrice;
    public TableColumn colQtyOnHnd;
    public TableColumn colSupplierID;

    public void initialize(){
        colItemId.setCellValueFactory(new PropertyValueFactory<>("item_ID"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("item_name"));
        colItemUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unit_price"));
        colQtyOnHnd.setCellValueFactory(new PropertyValueFactory<>("qty_on_hnd"));
        colSupplierID.setCellValueFactory(new PropertyValueFactory<>("supplier_ID"));

        getAllItems();
    }
    public void addItemFormOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage4 = new Stage();
        stage4.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/studiosystem/view/AddItemForm.fxml"))));
        //stage2.centerOnScreen();
        //stage2.setFullScreen(true);
        stage4.initStyle(StageStyle.UNDECORATED);
        stage4.setX(500);
        stage4.setY(220);


        stage4.show();
        getAllItems();
        tblItem.refresh();
    }

    public void refreshOnAction(ActionEvent actionEvent) {
        getAllItems();
    }

    public void updateItemFormOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage5 = new Stage();
        stage5.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/studiosystem/view/UpdateItemForm.fxml"))));
        //stage2.centerOnScreen();
        //stage2.setFullScreen(true);
        stage5.initStyle(StageStyle.UNDECORATED);
        stage5.setX(1080);
        stage5.setY(220);


        stage5.show();
        tblItem.refresh();
    }

    public void searchItemOnAction(ActionEvent actionEvent) {
    }
    public void getAllItems(){
        ObservableList data = FXCollections.observableArrayList();
        try {
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM item");
            ResultSet allItems=statement.executeQuery();


            while (allItems.next()){
                //Button btn = new Button("delete");
                data.add(new Item(allItems.getString(1), allItems.getString(2),
                        allItems.getDouble(3),allItems.getInt(4),allItems.getString(5)));
            }


        } catch (SQLException e) {
            System.out.println("sql error");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("class not found error");
            throw new RuntimeException(e);
        }
        tblItem.setItems(data);
    }
}
