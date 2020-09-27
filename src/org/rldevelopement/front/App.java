package org.rldevelopement.front;


import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Duration;
import org.rldevelopement.back.Account;
import org.rldevelopement.back.network.SQLManager;
import org.rldevelopement.back.network.ServerManager;
import org.rldevelopement.back.security.Password;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;

public class App extends Application {

    public static Stage Stage = null;

    public static Account account = null;

    private static SQLManager sql;

    Socket socket = null;
    public static String systemipaddress = "";

    public static App instance;
    
    @Override
    public void start(Stage stage) throws Exception {
        Stage = stage;
        Stage.initStyle(StageStyle.DECORATED);

        instance = this;

        connectToDataBase();
        System.out.println(getPublicAdress());
        loadView("LoginDocument", "Login");
        ServerManager.connectToServer();
    }

    public void loadView(String res, String title) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../front/fxml/" + res + ".fxml"));

        Scene scene = new Scene(root);

        Stage.setScene(scene);
        Stage.setResizable(false);
        Stage.setTitle(title + " - Remote Classes");

        FadeTransition ft = new FadeTransition(Duration.millis(3000));
        ft.setFromValue(0.0);
        ft.setToValue(1.0);

        ft.play();
        Stage.show();
    }

    public static App getInstance() {
        return instance;
    }

    private String getPublicAdress() {
        try
        {
            URL url_name = new URL("http://bot.whatismyipaddress.com");

            BufferedReader sc =
                    new BufferedReader(new InputStreamReader(url_name.openStream()));

            systemipaddress = sc.readLine().trim();
        }
        catch (Exception e)
        {
            systemipaddress = "Cannot Execute Properly";
        }

        return systemipaddress;
    }

    private void connectToDataBase() {
        sql = new SQLManager("jdbc:mysql://", "localhost", "onlineclasses", "root", "");
        sql.connect();
    }

    public static Stage getStage(){
        return Stage;
    }

    public static void setAccount(Account account) {
        App.account = account;
    }

    public static String getSystemipaddress() {
        return systemipaddress;
    }

    public static Account getAccount() {
        return account;
    }
}
