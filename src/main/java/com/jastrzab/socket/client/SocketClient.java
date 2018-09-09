package com.jastrzab.socket.client;

import com.jastrzab.socket.client.observer.ObserverTask;
import com.jastrzab.socket.client.observer.ServerObserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {


    private String serverHost;
    private int serverPort;

    private Socket clientSocket;
    private PrintWriter socketOutput;
    private BufferedReader socketInput;

    /**
     * Connect to SocketServer
     * @param host host to connect ( ip or host )
     * @param port port on which server is running
     * @throws IOException
     */
    public void connectTo(String host, int port) throws IOException {

        clientSocket = new Socket(host, port);
        socketOutput = new PrintWriter(clientSocket.getOutputStream(), true);
        socketInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }


    /**
     * Send request and get response
     * @param data
     * @return response String data from server
     * @throws IOException
     */
    public String sendRequest( String data) {
        try{

        // send data
        socketOutput.println(data);
        // return response
        return socketInput.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Error when send request");
        }
    }

    /**
     *
     * @param serverObserver Lambda expression to receive data from server
     * @return observerTask object to stop observ
     */
    public ObserverTask setServerObserver(ServerObserver serverObserver) {

        ObserverTask observerTask = new ObserverTask(socketInput, serverObserver);
        observerTask.start();
        return observerTask;

    }

    /**
     * Disconnect connection to server
     * @throws IOException
     */
    public void disconnect() throws IOException {
        socketInput.close();
        socketOutput.close();
        clientSocket.close();
    }

}
