import java.io.*;
import java.net.*;

class TCPServerVowels {

    public static void main(String argv[]) throws Exception {

        ServerSocket welcomeSocket = new ServerSocket(6789);
        
        System.out.println("Waiting for client connection...");

        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            System.out.println("Client connected");

            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            String clientSentence = inFromClient.readLine();
			
			System.out.println("Recieved: " + clientSentence);

            String vowels = extractVowels(clientSentence);

            outToClient.writeBytes(vowels + '\n');
        }
    }

    private static String extractVowels(String sentence) {
        StringBuilder vowels = new StringBuilder();
        for (char c : sentence.toCharArray()) {
            if (isVowel(c)) {
                vowels.append(c);
            }
        }
        return vowels.toString();
    }

    private static boolean isVowel(char c) {
        return "AEIOUaeiou".indexOf(c) != -1;
    }
}