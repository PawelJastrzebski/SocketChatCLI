package com.jastrzab.socket.client;

import com.jastrzab.socket.client.observer.ObserverTask;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class SocketClientTest {

    @Test
    void testClient() throws IOException, InterruptedException {
        SocketClient socketClient = new SocketClient();

        socketClient.connectTo("localhost", 8081);
        String res = socketClient.sendRequest("Test");
        System.out.println(res);


        ObserverTask observerTask = socketClient.setServerObserver((data) -> {
            System.out.println("Date from observer: " + data);
        });

        observerTask.stopObserve();


    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new SocketClientTest().testClient();
    }

}