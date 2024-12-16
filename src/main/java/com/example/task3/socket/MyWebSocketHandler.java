//package com.example.task3.socket;
//
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//import java.io.IOException;
//
//@Component
//@AllArgsConstructor
//public class MyWebSocketHandler extends TextWebSocketHandler {
//
//    private final WebSocketSessionManager sessionManager;
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        sessionManager.addSession(session);
//    }
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        sessionManager.removeSession(session);
//    }
//
//    public void sendAlert(String deviceId, String alertMessage) {
//        for (WebSocketSession session : sessionManager.getSessions()) {
//            if (session.getUri().getPath().contains(deviceId)) {
//                try {
//                    session.sendMessage(new TextMessage(alertMessage));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//}
