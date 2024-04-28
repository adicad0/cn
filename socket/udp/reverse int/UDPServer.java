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

                int reversedNumber = reverseNumber(number);

                byte[] sendData = String.valueOf(reversedNumber).getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
                serverSocket.send(sendPacket);

                System.out.println("Reversed number sent to client.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int reverseNumber(int number) {
        int reversedNumber = 0;
        while (number != 0) {
            int digit = number % 10;
            reversedNumber = reversedNumber * 10 + digit;
            number /= 10;
        }
        return reversedNumber;
    }
}
