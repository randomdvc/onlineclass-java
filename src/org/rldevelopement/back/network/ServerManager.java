package org.rldevelopement.back.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerManager {

    private static InetAddress host = null;
    private static Socket socket = null;
    private static ObjectOutputStream oos = null;
    private static ObjectInputStream ois = null;

    public static void connectToServer() throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        host = InetAddress.getLocalHost();
        //establish socket connection to server
        socket = new Socket(host.getHostName(), 9876);
        System.out.println("Connect To Server !");
        //write to socket using ObjectOutputStream
        oos = new ObjectOutputStream(socket.getOutputStream());
        System.out.println("Sending request to Socket Server");
        oos.writeObject("Hi");
        //read the server response message
        ois = new ObjectInputStream(socket.getInputStream());
        String message = (String) ois.readObject();
        System.out.println("Message: " + message);
        //close resources
        ois.close();
        oos.close();
        Thread.sleep(100);
    }

    public static Object sendToServer(Object object) throws IOException {

        oos.writeObject(object);

        Object response = ois.read();

        return response;
    }

}
