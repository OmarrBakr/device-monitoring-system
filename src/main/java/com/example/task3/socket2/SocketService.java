package com.example.task3.socket2;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SocketService {

    private final SocketIOServer server;

    public SocketService(SocketIOServer server) {
        this.server = server;
    }

    public void sendMessageToAllInRoom(String room, String eventName, String message) {
        for (SocketIOClient client : server.getRoomOperations(room).getClients()) {
            client.sendEvent(eventName, new Message(MessageType.SERVER, message));
        }
    }
}

