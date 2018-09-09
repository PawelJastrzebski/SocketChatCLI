package com.jastrzab.chat.api.chatClient;

import com.jastrzab.chat.api.chatServer.ChatServer;

public class App {
    public static void main(String[] args) {

        ChatClient chatClient = new ChatClient();


        chatClient.logIn("Pawel");
    }
}
