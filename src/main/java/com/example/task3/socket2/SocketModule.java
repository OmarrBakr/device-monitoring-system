package com.example.task3.socket2;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.example.task3.config.JwtService;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SocketModule {

    private final SocketIOServer server;
    private final SocketService socketService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public SocketModule(SocketIOServer server,
                        SocketService socketService,
                        JwtService jwtService,
                        UserDetailsService userDetailsService) {
        this.server = server;
        this.socketService = socketService;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;

        server.addConnectListener(onConnected());
        server.addDisconnectListener(onDisconnected());
        server.addEventListener("get_alerts", Message.class, onChatReceived());
    }

    private ConnectListener onConnected() {
        return client -> {
            String token = client.getHandshakeData().getHttpHeaders().get("Authorization");

            if (token == null || !token.startsWith("Bearer ")) {
                client.disconnect();
                log.warn("Client[{}] - Missing or invalid Authorization token", client.getSessionId().toString());
                return;
            }
            try {
                token = token.substring(7);
                String username = jwtService.extractUsername(token);
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if (username == null || !jwtService.isTokenValid(token, userDetails)) {
                    client.disconnect();
                    log.warn("Client[{}] - Invalid JWT token", client.getSessionId().toString());
                    return;
                }
                String room = client.getHandshakeData().getSingleUrlParam("room");
                client.joinRoom(room);
                log.info("Socket ID[{}] connected to room [{}] as [{}]", client.getSessionId(), room, username);
            }catch (Exception e){
                client.disconnect();
                System.out.println(e.getMessage());
            }
            };
    }

    private DisconnectListener onDisconnected() {
        return client -> log.info("Client[{}] - Disconnected from socket", client.getSessionId().toString());
    }

    private DataListener<Message> onChatReceived() {
        return (senderClient, data, ackSender) -> {
            log.info("Received message: {}", data.toString());
            socketService.sendMessageToAllInRoom(data.getRoom(), "get_alerts", data.getMessage());
        };
    }
}
