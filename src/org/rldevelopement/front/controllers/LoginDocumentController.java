/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rldevelopement.front.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.rldevelopement.back.Account;
import org.rldevelopement.back.network.SQLManager;
import org.rldevelopement.back.security.Password;
import org.rldevelopement.front.App;

/**
 *
 * @author danml
 */
public class LoginDocumentController implements Initializable {
    
    @FXML
    private Label label;

    @FXML
    private JFXTextField email;
    @FXML
    private JFXPasswordField password;

    private Account account;

    @FXML
    private void handleLoginButtonAction(ActionEvent event) throws IOException {

        try {
            account = SQLManager.getAccount(email.getText(), password.getText());
            System.out.println("Connected to " + account.getEmail());
            if(account.getCycle() != null || account.getLevel() != 0 || account.getClassName() != null) {
                Stage stage = App.getStage();
                Parent root = FXMLLoader.load(getClass().getResource("../fxml/ChoosePage.fxml"));

                Scene scene = new Scene(root);

                stage.setTitle("Home - Remote Classes");
                stage.setScene(scene);
                stage.setResizable(false);


                stage.show();
            } else {
                Stage stage = App.getStage();
                Parent root = FXMLLoader.load(getClass().getResource("../fxml/MainPage.fxml"));

                Scene scene = new Scene(root);

                stage.setTitle("Home - Remote Classes");
                stage.setScene(scene);
                stage.setResizable(false);


                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    public void handleRegisterButtonAction(ActionEvent actionEvent) throws IOException {
        Stage stage = App.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/RegisterDocument.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("Home - Remote Classes");
        stage.setScene(scene);
        stage.setResizable(false);


        stage.show();
    }
}
