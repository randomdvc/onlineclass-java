package org.rldevelopement.front.controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.rldevelopement.back.network.SQLManager;
import org.rldevelopement.front.App;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreateClassController implements Initializable {

    @FXML
    private JFXTextField subjectField;

    @FXML
    private JFXTextField startField;

    @FXML
    private JFXTextField durationField;

    @FXML
    private JFXTextField levelField;

    @FXML
    private JFXTextField cycleField;

    @FXML
    private JFXTextField classField;

    public String subject;

    public String start;

    public int duration;

    public int level;

    public String cycle;

    public int Class;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleLoginButtonAction(ActionEvent actionEvent) throws IOException {
        subject = subjectField.getText();
        start = startField.getText();
        duration = Integer.parseInt(durationField.getText());
        level = Integer.parseInt(levelField.getText());
        cycle = cycleField.getText();
        Class = Integer.parseInt(classField.getText());

        try {
            SQLManager.createClass(subject, App.getAccount().getEmail(), App.getSystemipaddress(), 25565, 0, start, duration, level, cycle, Class);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Stage stage = App.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/Classroom.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("Home - Remote Classes");
        stage.setScene(scene);
        stage.setResizable(false);


        stage.show();

    }
}
