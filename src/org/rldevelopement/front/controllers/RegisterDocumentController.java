package org.rldevelopement.front.controllers;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.rldevelopement.back.Account;
import org.rldevelopement.back.network.SQLManager;
import org.rldevelopement.back.security.Password;
import org.rldevelopement.front.App;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterDocumentController implements Initializable {

    @FXML
    private JFXTextField fname;
    @FXML
    private JFXTextField lname;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXCheckBox teacher;

    private String type;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleLoginButtonAction(ActionEvent actionEvent) throws Exception {

        if(teacher.isSelected()){
            type = "teacher";
        } else {
            type = "student";
        }

        SQLManager.createAccount(fname.getText(), lname.getText(), email.getText(), password.getText(), type);
        App.setAccount(SQLManager.getAccount(email.getText(), password.getText()));
        Stage stage = App.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/MainPage.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("Home - Remote Classes");
        stage.setScene(scene);
        stage.setResizable(false);


        stage.show();

        System.out.println(fname.getText() + " " + lname.getText() + " " + email.getText() + " " + Password.getSaltedHash(password.getText()));

    }



}
