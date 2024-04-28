import java.io.*;
import java.net.*;

class TCPClientVowels {

    public static void main(String argv[]) throws Exception {

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket = new Socket("localhost", 6789);
        
        System.out.println("Server connected");

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        System.out.print("Enter a sentence: ");
        String inputSentence = inFromUser.readLine();
        outToServer.writeBytes(inputSentence + "\n");

        String vowels = inFromServer.readLine();
        System.out.println("Vowels from server: " + vowels);

        clientSocket.close();
    }
}