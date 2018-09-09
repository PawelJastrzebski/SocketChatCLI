package com.jastrzab.socket.server.clientRequest;

import java.io.PrintWriter;

public class Response {

    private PrintWriter socketOutput;
    private boolean wasSend = false;

    public Response(PrintWriter socketOutput) {
        this.socketOutput = socketOutput;
    }

    /**
     * Send response to Client
     * @param data String data to client
     */
    public void sendResponse(String data) {
        if (!wasSend) {

            socketOutput.println(data);
            wasSend = true;
        }
    }

    public boolean wasSended() {
        return this.wasSend;
    }

}
