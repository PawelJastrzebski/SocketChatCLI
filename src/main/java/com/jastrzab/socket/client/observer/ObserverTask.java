package com.jastrzab.socket.client.observer;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Task which observe socketServer
 * wait for new data
 * pass data to ServerObserver
 */
public class ObserverTask extends Thread {

    private BufferedReader SocketInput;
    private ServerObserver serverObserver;
    private boolean isDone = false;


    public ObserverTask(BufferedReader socketInput, ServerObserver serverObserver) {
        SocketInput = socketInput;
        this.serverObserver = serverObserver;
    }

    @Override
    public void run() {

        try {

            String receivedData;
            while ((receivedData = SocketInput.readLine()) != null && !isDone) {
                serverObserver.onReceivedData(receivedData);
            }

        } catch (IOException e) {
            isDone = true;
        }

    }
    public void stopObserve(){
        this.isDone = true;
    }
}
