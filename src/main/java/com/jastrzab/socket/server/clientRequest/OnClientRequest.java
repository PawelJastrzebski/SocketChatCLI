package com.jastrzab.socket.server.clientRequest;

public interface OnClientRequest {
    void clientRequest(Request request, Response response) throws InterruptedException;
}
