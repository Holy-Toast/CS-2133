import java.io.*;
import java.net.*;

public class Server implements Runnable {
    private Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
            try {
                BufferedReader in =
                        new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line;
                while((line = in.readLine()) != null) {
                    String[] parts = line.split(" ");
                    File file;
                    try {
                        if (parts[0].equalsIgnoreCase("get")) {
                            if (parts[2].equalsIgnoreCase("http/1.1")) {

                                String fileName = parts[1];

                                if (fileName.equalsIgnoreCase("/")) {
                                    file = new File("/index.html");
                                    writeFile(file);
                                }
                                else {
                                    boolean found = false;
                                    for(int i=0; i<fileName.length()-1; i++) {
                                        char c = fileName.charAt(i);
                                        char x = fileName.charAt(i+1);
                                        if(c == '.' && x == '.') {
                                            writeError(500);
                                            found = true;
                                            break;
                                        }
                                    }
                                    if(!found) {
                                        file = new File(fileName);
                                        writeFile(file);
                                    }
                                }
                            }
                        } else {
                            writeError(500);
                        }
                    } catch (IndexOutOfBoundsException e) {
                        writeError(500);
                    }
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            }catch(IOException e) {
                e.printStackTrace();
            }
    }

    private void writeFile(File file) {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new FileReader(file));
            out.print("HTTP/1.1 200 OK\r\n");
            out.print("Content-type: text/html\r\n\r\n");
            out.flush();
            String line;
            while((line = in.readLine()) != null) {
                out.print(line);
                out.flush();
            }
            out.close();
            in.close();
        } catch(FileNotFoundException e) {
            writeError(404);
            System.out.println(file.getName());
        } catch(IOException e) {
            writeError(500);
            e.printStackTrace();
        }

    }

    private void writeError(int errorType) {
        String error;

        if(errorType == 500) {
            error = "HTTP/1.1 500 Internal Server Error\r\n\r\n";
        }
        else if(errorType == 404) {
           error = "HTTP/1.1 404 Not Found\r\n\r\n";
        }
        else {
            error = "HTTP/1.1 500 Internal Server Error\r\n\r\n";
        }

        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            out.print(error);
            out.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
