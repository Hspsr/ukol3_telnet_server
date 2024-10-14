package utb.fai;

import java.io.*;
import java.net.*;

public class ClientThread extends Thread {

    private Socket clientSocket;

    public ClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try{
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter serverWriter = new PrintWriter(clientSocket.getOutputStream(), true);

            String message;
            while ((message = inputReader.readLine()) != null) {
                serverWriter.println(message);
            }
            inputReader.close();
            serverWriter.close();
            clientSocket.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
