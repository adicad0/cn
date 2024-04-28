import java.io.*;
import java.net.*;

class TCPServer
{
   public static void main(String argv[]) throws Exception
      {
         String clientSentence;
         String capitalizedSentence;
         ServerSocket welcomeSocket = new ServerSocket(6789);
		 
		 System.out.println("Waiting for client Connection .....");


         while(true)
         {
            Socket connectionSocket = welcomeSocket.accept();
			
			 System.out.println("Client Connected");
			 
            BufferedReader inFromClient =
               new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			   
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			
            clientSentence = inFromClient.readLine();
			
            System.out.println("Received: " + clientSentence);
			
            capitalizedSentence = clientSentence.toUpperCase() + '\n';
			
            outToClient.writeBytes(capitalizedSentence);
         }
      }
}