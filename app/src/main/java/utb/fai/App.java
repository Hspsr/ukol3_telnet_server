package utb.fai;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);
        int max_threads = Integer.parseInt(args[1]);
        Socket clientSocket;
        
        ThreadPoolExecutor threadsHandler = new ThreadPoolExecutor(max_threads, max_threads,0L,TimeUnit.MILLISECONDS,new LinkedBlockingDeque<Runnable>());

        // Implementation of the main server loop
        try{
            ServerSocket serverSocket = new ServerSocket(port);

            while(!serverSocket.isClosed()){
                clientSocket = serverSocket.accept();

                threadsHandler.execute(new ClientThread(clientSocket));
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        
        threadsHandler.shutdown();
    }
}
