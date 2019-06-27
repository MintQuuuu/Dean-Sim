package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    Button login_button;
    @FXML
    Button exit_button;
    @FXML
    TextField login_field;
    @FXML
    PasswordField password_field;


    @FXML
    public void loginPressed(ActionEvent event){
    if (password_field.getText().equals("admin") && login_field.getText().equals("admin"))
        sample.Main.goToNewScene();
    else{
        password_field.clear();
        login_field.clear();
    }

    }
    @FXML
    public void exitPressed(ActionEvent event){
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();

    }

}
