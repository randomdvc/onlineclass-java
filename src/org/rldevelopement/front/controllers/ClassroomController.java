package org.rldevelopement.front.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.net.URL;
import java.util.ResourceBundle;

public class ClassroomController implements Initializable {

    String filename = "http://techslides.com/demos/sample-videos/small.mp4";

    MediaPlayer oracleVid = new MediaPlayer(
            new Media(filename)
    );

    @FXML
    MediaView mediaView = new MediaView();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mediaView.setMediaPlayer(oracleVid);

        oracleVid.play();

    }
}
