import java.io.*;
import java.net.*;

class TCPClientReverseInt {

    public static void main(String argv[]) throws Exception {

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket = new Socket("localhost", 6789);
		
		System.out.println("Server Connected");

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        System.out.print("Enter an integer: ");
        String input = inFromUser.readLine();
        outToServer.writeBytes(input + '\n');

        String reversedInteger = inFromServer.readLine();
        System.out.println("Reversed integer from server: " + reversedInteger);

        clientSocket.close();
    }
}