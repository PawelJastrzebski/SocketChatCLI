package com.jastrzab.socket.server;

import com.jastrzab.socket.server.clientRequest.OnClientRequest;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketServer {


    private ServerSocket serverSocket;
    private int serverPort;
    private boolean isRunning = false;
    private List<Client> clients = new ArrayList<>();

    /**
     * Socket SocketServer
     *
     * @param port port on which the server will be started.
     */
    public SocketServer(int port) {
        this.serverPort = port;
    }

    public void start(OnClientRequest onClientRequest)  {

        try {

        System.out.println("SocketServer started at port: " + this.serverPort);
        serverSocket = new ServerSocket(this.serverPort);
        isRunning = true;

        }catch (IOException e){
            System.out.println("Error with ServerSocket");
        }


        // Server response Loop
        new Thread(() -> {

            try {

                while (isRunning) {
                    Socket clientSocket = serverSocket.accept();
                    ConnectionTask connectionTask = new ConnectionTask(clientSocket, onClientRequest);
                    connectionTask.start();
                    this.clients.add(connectionTask.getClient());
                }
            } catch (IOException e) {}

        }).start();


    }

    public List<Client> getClients() {
        return clients;
    }

    public void stop() throws IOException {
        isRunning = false;
        serverSocket.close();
    }


}
