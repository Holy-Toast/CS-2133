import java.io.*;
import java.net.*;

public class Webserver {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9001);
            while(true) {
                System.out.println("Waiting...");
                Socket server = serverSocket.accept();
                Thread connection =
                        new Thread(new Client(server));
                System.out.println("Connected!");
                connection.start();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
