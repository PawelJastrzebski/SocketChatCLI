package com.jastrzab.socket.server;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class SocketServerTest {

    @Test
    public void runServerTest() throws IOException, InterruptedException {

        //given
        SocketServer socketServer = new SocketServer(8081);
        socketServer.start((request, response) -> {

            System.out.println("SocketClient DTO Received");

            response.sendResponse("Test data res");

            Thread.sleep(1000);

            request.getClient().sendResponse("Test response to client");
        });


        while (true) {

            Thread.sleep(2000);
            System.out.println("Clients");
            for (Client client : socketServer.getClients()) {

                System.out.println("ID: "+ client.getClientId()+" isActive "+client.connectionIsActive());
            }

        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new SocketServerTest().runServerTest();
    }

}