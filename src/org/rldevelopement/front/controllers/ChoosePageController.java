package org.rldevelopement.front.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.rldevelopement.back.Class;
import org.rldevelopement.back.matters;
import org.rldevelopement.back.network.SQLManager;
import org.rldevelopement.front.App;
import org.rldevelopement.front.controllers.ListCellController;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ChoosePageController implements Initializable {
    @FXML
    private ListView<Class> ListView;
    ArrayList<Class> test = new ArrayList<>();

    ObservableList<Class> list;

    ArrayList<Class> classes;
    public static Class SelectedClass = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            classes = SQLManager.getClasses();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        list = FXCollections.observableArrayList(classes);
        ListView.setItems(list);
        ListView.setCellFactory(ClassListView -> new ListCellController());

        ListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Class>() {
            @Override
            public void changed(ObservableValue<? extends Class> observable, Class oldValue, Class newValue) {
                System.out.println(newValue.getClassId());
                ChoosePageController.setSelectedClass(newValue);
            }
        });
    }

    public static void setSelectedClass(Class selectedClass) {
        SelectedClass = selectedClass;
    }

    public static Class getSelectedClass() {
        return SelectedClass;
    }

    public void handleLoginButtonAction(ActionEvent actionEvent) throws IOException {
        if(getSelectedClass() != null) {
            System.out.println("id = " + getSelectedClass().getClassId() + " matter = " + getSelectedClass().getMatter() + " date = " + getSelectedClass().getDate() + " duration = " + getSelectedClass().getDuration());
            Stage stage = App.getStage();
            Parent root = FXMLLoader.load(getClass().getResource("../fxml/Classroom.fxml"));

            Scene scene = new Scene(root);

            stage.setTitle("Home - Remote Classes");
            stage.setScene(scene);
            stage.setResizable(false);


            stage.show();
        }else {
            System.out.println("Please Select a Class");
            //SQLManager.createClass("arabic", "test4", App.getSystemipaddress(), 25567, 5, "11:30", 90, 3, "college", 3);
        }
    }
}
