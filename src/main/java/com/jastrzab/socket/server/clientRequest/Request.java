package com.jastrzab.socket.server.clientRequest;

import com.jastrzab.socket.server.Client;

public class Request {

    String data;
    Client client;

    public Request(String data, Client client) {
        this.data = data;
        this.client = client;
    }
    public String getData(){
        return this.data;
    }

    public Client getClient() {
        return client;
    }
}
