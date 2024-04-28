// UDPServer.java
import java.io.*;
import java.net.*;

public class UDPServer {
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(12345);
            System.out.println("Server waiting for client on port 12345...");

            while (true) {
                byte[] receiveData = new byte[BUFFER_SIZE];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                String inputStr = new String(receivePacket.getData(), 0, receivePacket.getLength());

                int number = Integer.parseInt(inputStr);
                int factorial = calculateFactorial(number);

                byte[] sendData = String.valueOf(factorial).getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
                serverSocket.send(sendPacket);

                System.out.println("Factorial of " + number + " sent to client.");
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
