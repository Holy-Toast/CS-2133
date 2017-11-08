import java.net.*;
import java.io.*;

public class Webclient {

    public Webclient() {

        try {
            Socket socket = new Socket("localhost", 9001);
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.print("GET /test.txt HTTP/1.1\r\n");
            out.flush();

            String line;
            while((line = in.readLine()) != null) {
                System.out.println(line);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        /*
        try {
            File file = new File("test.txt");
            BufferedReader in = new BufferedReader(new FileReader(file));
            String line;
            while((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        */
    }


    public static void main(String[] args) {
        new Webclient();
    }
}
