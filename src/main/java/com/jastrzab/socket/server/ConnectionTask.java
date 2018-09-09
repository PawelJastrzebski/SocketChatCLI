package com.jastrzab.socket.server;

import com.jastrzab.socket.server.clientRequest.OnClientRequest;
import com.jastrzab.socket.server.clientRequest.Request;
import com.jastrzab.socket.server.clientRequest.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionTask extends Thread {

    private PrintWriter out;
    private BufferedReader in;
    private Socket clientSocket;
    private OnClientRequest onClientRequest;
    private boolean isActive = true;
    private Client client;


    public ConnectionTask(Socket clientSocket, OnClientRequest onClientRequest) {

        this.clientSocket = clientSocket;
        this.onClientRequest = onClientRequest;

        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            client = Client.newClient(this.out, this);;

        } catch (Exception e) {
            //System.out.println("Error when create ConnectionTask");
            isActive = false;
        }

    }

    // Start new connection
    public void run() {
        startObserveForClientRequest();
    }


    private void startObserveForClientRequest(){

        try {

            String serializedMap;
            while ((serializedMap = in.readLine()) != null && this.isActive) {
                Request request = new Request(serializedMap,client);
                Response response = new Response(out);
                this.onClientRequest.clientRequest(request,response);

                // must send response to finalize ClientRequest
                if(!response.wasSended()){
                    response.sendResponse(null);
                }

            }

        } catch (Exception e) {
            isActive = false;
            //System.out.println("Error in ConnectionTask");
        }

    }

    public void closeConnection() throws IOException {

        isActive = false;
        clientSocket.close();
        in.close();
        out.close();
    }

    public boolean isActive() {
        return isActive;
    }

    public Client getClient() {
        return client;
    }
}
