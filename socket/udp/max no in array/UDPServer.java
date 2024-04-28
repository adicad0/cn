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

                String arrayStr = new String(receivePacket.getData(), 0, receivePacket.getLength());
                String[] strNumbers = arrayStr.split(",");
                int[] numbers = new int[strNumbers.length];
                for (int i = 0; i < strNumbers.length; i++) {
                    numbers[i] = Integer.parseInt(strNumbers[i]);
                }

                int max = findMax(numbers);

                String maxStr = String.valueOf(max);

                byte[] sendData = maxStr.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
                serverSocket.send(sendPacket);

                System.out.println("Max number sent to client.");
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
