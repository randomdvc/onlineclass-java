package org.rldevelopement.front.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.rldevelopement.back.Class;

import java.io.IOException;

public class ListCellController extends ListCell<Class> {


    @FXML
    private AnchorPane anchorPane;

    public FXMLLoader mLLoader = null;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @Override
    protected void updateItem(Class CLASS, boolean empty) {
        super.updateItem(CLASS, empty);

        if (empty || CLASS == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("../fxml/ListCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            label1.setText(CLASS.getMatter());
            label2.setText(CLASS.getDate());
            label3.setText(CLASS.getDuration() + " min");

            setText(null);
            setGraphic(anchorPane);
        }

    }
}
