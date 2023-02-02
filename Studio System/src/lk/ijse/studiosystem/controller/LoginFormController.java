package lk.ijse.studiosystem.controller;

import animatefx.animation.BounceIn;
import animatefx.animation.FadeInDown;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import javax.management.Notification;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginFormController {
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public AnchorPane loginContext;

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        if (txtUserName.getText().equals("admin") && txtPassword.getText().equals("1234")){
//            String URL ="/lk/ijse/studiosystem/view/AdminForm.fxml";
//            FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource(URL));
//            Parent root1 =fxmlLoader.load();
//            Stage stage1 =Stage();
//            stage1.setScene(new Scene(root1));
//            stage1.show();
            
            Stage stage1 = new Stage();
            stage1.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/studiosystem/view/AdminForm.fxml"))));
            //stage1.setFullScreen(true);
            stage1.initStyle(StageStyle.UNDECORATED);

            stage1.show();

            closeStage(loginContext);

            


        }else if(txtUserName.getText().equals("cashier") && txtPassword.getText().equals("1234")){
           // new FadeInDown(txtUserName).play();
            Stage stage8 = new Stage();
            stage8.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/studiosystem/view/CashierForm.fxml"))));
            //stage1.setFullScreen(true);
            stage8.initStyle(StageStyle.UNDECORATED);

            stage8.show();

            closeStage(loginContext);



        }else {
            Image image=new Image("/lk/ijse/studiosystem/asset/Thermbond-Icons-CAUTION-SHAKE-NEW.gif");
            Notifications .create()
                    .title("Photography Studio")
                    .graphic(new ImageView(image))
                    .text("Invalid Username or Password")
                    .hideAfter(Duration.seconds(5))
                    .show();

        }
    }

    private void closeStage(AnchorPane anchorPane){
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
    }

    public void keyPressedOnUserName(KeyEvent keyEvent) {
//        Pattern contactPattern = Pattern.compile("^[a-zA-Z]$");
//        Matcher matcher = contactPattern.matcher(txtUserName.getText());
//
//        boolean isMatch =matcher.matches();
//
//        if (!isMatch) {
//            txtUserName.setFocusColor(Paint.valueOf("red"));
//            System.out.println("invalid");
//
//            //Alert alert = new Alert(Alert.AlertType.ERROR);// line 1
//        } else {
//            txtUserName.setFocusColor(Paint.valueOf("green"));
//        }
    }
}




