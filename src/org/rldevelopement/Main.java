package org.rldevelopement;

import javafx.application.Application;
import org.rldevelopement.back.network.SQLManager;
import org.rldevelopement.front.App;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Application.launch(App.class, args);

    }

}
