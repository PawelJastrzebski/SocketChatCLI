package com.jastrzab.socket.client.observer;

import java.util.Map;

/**
 *  Observer used to received from SocketServer
 */
@FunctionalInterface
public interface ServerObserver {
    void onReceivedData(String data);
}
