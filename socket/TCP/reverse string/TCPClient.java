// TCPClient.java
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Connected to server.");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // Take input from the user
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a string: ");
            String inputStr = scanner.nextLine();

            // Send string to server
            writer.write(inputStr);
            writer.newLine();
            writer.flush();

            // Receive reversed string from server
            String response = reader.readLine();
            System.out.println("Reversed string received from server: " + response);

            socket.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
