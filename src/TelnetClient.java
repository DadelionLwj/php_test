import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TelnetClient {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // 服务器地址
        int port = 8765; // 端口号

        try (Socket socket = new Socket(serverAddress, port);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            // 向服务器发送消息
            while(true) {
                Scanner sc = new Scanner(System.in);
                String str = sc.nextLine();
                System.out.println(str);
                if (str.equals("shut down")) {
                    break;
                }
                if(str.equals("conv_tree")){
                    function();
                }
                out.println(str);

                // 读取服务器响应
                String response;
                while ((response = in.readLine()) != null) {
                    System.out.println("Received from server: " + response);
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void function(){

    }

}