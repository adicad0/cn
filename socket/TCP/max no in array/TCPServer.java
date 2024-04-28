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

                // Read array from client
                String arrayStr = reader.readLine();
                String[] strNumbers = arrayStr.split(",");
                int[] numbers = new int[strNumbers.length];
                for (int i = 0; i < strNumbers.length; i++) {
                    numbers[i] = Integer.parseInt(strNumbers[i]);
                }

                // Find maximum number
                int max = findMax(numbers);

                // Send maximum number back to client
                writer.write(String.valueOf(max));
                writer.newLine();
                writer.flush();

                System.out.println("Max number sent to client.");
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int findMax(int[] array) {
        int max = Integer.MIN_VALUE;
        for (int num : array) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }
}
