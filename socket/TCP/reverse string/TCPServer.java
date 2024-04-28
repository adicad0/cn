// TCPServer.java
import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server waiting for client on port 12345...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected.");

                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                // Read string from client
                String inputStr = reader.readLine();

                // Reverse the string
                String reversedStr = reverseString(inputStr);

                // Send reversed string back to client
                writer.write(reversedStr);
                writer.newLine();
                writer.flush();

                System.out.println("Reversed string sent to client.");
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }
}
