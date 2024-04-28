import java.io.*;
import java.net.*;

class TCPServerPalindrome {
    public static void main(String argv[]) throws Exception {
        ServerSocket welcomeSocket = new ServerSocket(6789);
        
        System.out.println("Waiting for client connection...");
        
        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            System.out.println("Client connected");
            
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            
            String clientSentence = inFromClient.readLine();
            
            System.out.println("Received: " + clientSentence);
            
            boolean isPalindrome = checkPalindrome(clientSentence);
            
            if (isPalindrome) {
                outToClient.writeBytes("The string is a palindrome\n");
            } else {
                outToClient.writeBytes("The string is not a palindrome\n");
            }
            
            connectionSocket.close(); // Close the connection after sending the response
        }
    }
    
    private static boolean checkPalindrome(String str) {
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