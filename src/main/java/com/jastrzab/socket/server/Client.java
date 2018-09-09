package com.jastrzab.socket.server;

import java.io.IOException;
import java.io.PrintWriter;

public class Client {

    // static constructor
    private static long clientNextId = 0;
    public static Client newClient(PrintWriter socketOutput , ConnectionTask connectionTask){
        return new Client(clientNextId++,socketOutput, connectionTask);
    }

    private long clientId;
    private PrintWriter socketOutput;
    private ConnectionTask connectionTask;

    // private constructor
    private Client(long clientId, PrintWriter socketOutput , ConnectionTask connectionTask ) {
        this.clientId = clientId;
        this.socketOutput = socketOutput;
        this.connectionTask =  connectionTask;
    }

    /**
     *
     * @param data String data to send to client
     * @return return succeed status
     */
    public boolean sendResponse(String data){

        if(connectionTask.isActive()){
            socketOutput.println(data);
            return true;
        }
        return false;

    }

    /**
     *
     * @return clientID
     */
    public long getClientId() {
        return this.clientId;
    }

    /**
     * Close Connection to Client
     * after that you cant send response to client because socket connection is closed
     */
    public void closeClientConnection(){

        try {
            connectionTask.closeConnection();
        } catch (IOException e) {}

    }

    public boolean connectionIsActive(){
        return connectionTask.isActive();
    }
}
