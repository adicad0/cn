// TCPClient.java for factorial
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
            System.out.print("Enter a number to calculate factorial: ");
            int number = scanner.nextInt();

            // Send number to server
            writer.write(String.valueOf(number));
            writer.newLine();
            writer.flush();

            // Receive factorial from server
            String response = reader.readLine();
            System.out.println("Factorial received from server: " + response);

            socket.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

