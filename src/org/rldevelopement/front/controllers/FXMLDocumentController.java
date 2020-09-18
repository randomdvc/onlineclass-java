package org.rldevelopement.front.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.rldevelopement.back.network.SQLManager;
import org.rldevelopement.front.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLDocumentController implements Initializable {

    @FXML // fx:id="SchoolCombo"
    private ComboBox<String> SchoolCombo;
    @FXML // fx:id="CycleCombo"
    private ComboBox<String> CycleCombo;
    @FXML // fx:id="LevelCombo"
    private ComboBox<String> LevelCombo;
    @FXML // fx:id="ClassCombo"
    private ComboBox<String> ClassCombo;
    @FXML // fx:id="AnchorPane2"
    private AnchorPane AnchorPane2;

    public static String School;
    public static String Cycle;
    public static String Level;
    public static String Class;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SchoolCombo.getItems().setAll("LaPréference", "Flowers", "Mamounia");
        CycleCombo.getItems().setAll("college", "lycee");
        LevelCombo.getItems().setAll("1", "2", "3");
        ClassCombo.getItems().setAll("1", "2", "3", "4");
        SchoolCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue<? extends String> selected, String oldSelection, String newSelection) {
                if (newSelection != null) {
                    School = newSelection;
                }
            }
        });
        CycleCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue<? extends String> selected, String oldSelection, String newSelection) {
                if (newSelection != null) {
                    Cycle = newSelection;
                }
            }
        });
        LevelCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue<? extends String> selected, String oldSelection, String newSelection) {
                if (newSelection != null) {
                    Level = newSelection;
                }
            }
        });
        ClassCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue<? extends String> selected, String oldSelection, String newSelection) {
                if (newSelection != null) {
                    Class = newSelection;
                }
            }
        });
    }

    public void handleLoginButtonAction(ActionEvent actionEvent) throws IOException {
        if(School != null && Cycle != null && Level != null && Class != null) {
            System.out.println("School : " + School);
            System.out.println("Cycle : " + Cycle);
            System.out.println("Level : " + Level);
            System.out.println("Class : " + Class);
            SQLManager.updateClassInfo("cycle", Cycle, App.getAccount().getEmail());
            SQLManager.updateClassInfo("level", Level, App.getAccount().getEmail());
            SQLManager.updateClassInfo("class", Class, App.getAccount().getEmail());
            Stage stage = App.getStage();
            Parent root = FXMLLoader.load(getClass().getResource("../fxml/ChoosePage.fxml"));

            Scene scene = new Scene(root);

            stage.setTitle("Home - Remote Classes");
            stage.setScene(scene);
            stage.setResizable(false);


            stage.show();
        } else {
            System.out.println("Veuillez Remplir tous les Champs");
        }
    }
}
