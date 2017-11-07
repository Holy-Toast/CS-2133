import java.io.*;
import java.net.*;

public class Client implements Runnable {
    private Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader in =
                    new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while((line = in.readLine()) != null) {
                System.out.println(line);
            }
            //Line should equal GET <filename> HTTP/1.1
            //Extract the name of the file
            //If empty, <filename> = "/" : load index.html
            //Return: HTP/1.1 200 OK\r\n
            //        Content-type: text/html\r\n\r\n
            //Then return the file asked for
            //
            //If it doesn't exist return: HTTP/1.1 404 Not Found\r\n\r\n
            //If something else goes wrong return: HTTP/1.1 500 Internal Server Error\r\n\r\n
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
