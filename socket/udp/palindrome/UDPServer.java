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

                boolean isPalindrome = isPalindrome(inputStr);

                String palindromeResult = isPalindrome ? "Palindrome" : "Not Palindrome";

                byte[] sendData = palindromeResult.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
                serverSocket.send(sendPacket);

                System.out.println("Palindrome check result sent to client.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
