import java.io.*;
import java.net.*;

class TCPClient
{
 public static void main(String argv[]) throws Exception
 {
  String sentence;
  
  String modifiedSentence;
  
  BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
  
  Socket clientSocket = new Socket("localhost", 6789);
  
  DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
  
  BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
  
  sentence = inFromUser.readLine();
  
  outToServer.writeBytes(sentence + '\n');
  modifiedSentence = inFromServer.readLine();
  System.out.println("FROM SERVER: " + modifiedSentence);
  clientSocket.close();
 }
}

//server:
//PS C:\Users\satish bhat\OneDrive\Desktop> javac TCPServer.java
//PS C:\Users\satish bhat\OneDrive\Desktop> java TCPServer
//Waiting for client Connection .....

//client
//PS C:\Users\satish bhat\OneDrive\Desktop> javac TCPClient.java
//PS C:\Users\satish bhat\OneDrive\Desktop> java TCPClient
//aditya (enter input0
//FROM SERVER: ADITYA  (output)