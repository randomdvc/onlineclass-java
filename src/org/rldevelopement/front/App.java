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
import org.rldevelopement.back.security.Password;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.Arrays;

public class App extends Application {

    public static Stage Stage = null;

    public static Account account = null;

    private static SQLManager sql;

    Socket socket = new Socket();
    public static String systemipaddress = "";

    @Override
    public void start(Stage stage) throws Exception {
        sql = new SQLManager("jdbc:mysql://", "localhost", "onlineclasses", "root", "");
        sql.connect();

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
        System.out.println("Public IP Address: " + systemipaddress +"\n");


        Stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("../front/fxml/LoginDocument.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle("Login - Remote Classes");

        stage.show();
        FadeTransition ft = new FadeTransition(Duration.millis(3000));
        ft.setFromValue(0.0);
        ft.setToValue(1.0);

        ft.play();
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
