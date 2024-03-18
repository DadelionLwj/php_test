import java.io.*;
import java.net.*;

public class TelnetServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8765)) {
            System.out.println("Server is listening on port 8765...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("New client connected.");

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                while(true){
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        System.out.println("Received: " + inputLine);
                        // 处理输入并发送响应
                        String[] part = inputLine.split(" ");
                        if(part[0].equals("mul")){
                            double num1 = Double.valueOf(part[1]);
                            double num2 = Double.valueOf(part[2]);
                            out.println(num1 + num2);
                        } else if (part[0].equals("incr")) {
                            out.println(1 + part[1]);
                        } else if (part[0].equals("div")) {
                            double num1 = Double.valueOf(part[1]);
                            double num2 = Double.valueOf(part[2]);
                            out.println(num1 / num2);
                        } else {
                            out.println("unknown command");
                        }
                    }
                    if(inputLine.equals("shut down")){
                        break;
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}