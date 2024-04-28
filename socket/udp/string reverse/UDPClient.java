// UDPClient.java
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();

            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 12345;

            // Take input from the user
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a string to reverse: ");
            String inputStr = scanner.nextLine();

            byte[] sendData = inputStr.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Reversed string received from server: " + response);

            clientSocket.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
