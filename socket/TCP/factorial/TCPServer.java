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

                String inputLine = reader.readLine();
                int number = Integer.parseInt(inputLine);
                int factorial = calculateFactorial(number);

                writer.write(String.valueOf(factorial));
                writer.newLine();
                writer.flush();

                System.out.println("Factorial of " + number + " sent to client.");
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int calculateFactorial(int n) {
        if (n == 0 || n == 1)
            return 1;
        else
            return n * calculateFactorial(n - 1);
    }
}
