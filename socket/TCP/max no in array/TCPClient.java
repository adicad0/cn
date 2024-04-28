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
            System.out.print("Enter numbers separated by comma to find max: ");
            String arrayStr = scanner.nextLine();

            // Send array to server
            writer.write(arrayStr);
            writer.newLine();
            writer.flush();

            // Receive maximum number from server
            String response = reader.readLine();
            System.out.println("Max number received from server: " + response);

            socket.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
