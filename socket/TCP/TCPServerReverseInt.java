import java.io.*;
import java.net.*;

class TCPServerReverseInt {

    public static void main(String argv[]) throws Exception {

        ServerSocket welcomeSocket = new ServerSocket(6789);
		
		System.out.println("Waiting for client Connection .....");

        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
			
            System.out.println("Client Connected");
			
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            String clientInput = inFromClient.readLine();
            int number = Integer.parseInt(clientInput);
			            System.out.println("Received: " + number);

            int reversedNumber = reverseNumber(number);

            outToClient.writeBytes(Integer.toString(reversedNumber) + '\n');
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