import java.io.*;
import java.net.*;

class TCPClientPalindrome {
    public static void main(String argv[]) throws Exception {
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket = new Socket("localhost", 6789);
        
        System.out.println("Server connected");
        
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        
        System.out.print("Enter a string: ");
        String inputString = inFromUser.readLine();
        
        outToServer.writeBytes(inputString + '\n');
        
        String serverResponse = inFromServer.readLine();
        
        System.out.println("FROM SERVER: " + serverResponse);
        
        clientSocket.close();
    }
}