import java.io.*;
import java.net.*;

public class Webserver {

    private Thread connection = null;

    public Webserver() {
        try {
            ServerSocket serverSocket = new ServerSocket(9001);
            while(true) {
                System.out.println("Waiting...");
                Socket server = serverSocket.accept();
                connection =
                        new Thread(new Server(server));
                System.out.println("Connected to " + server.getRemoteSocketAddress());
                connection.start();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Webserver();
    }
}
