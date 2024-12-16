//package com.example.task3.socket;
//
//import lombok.AllArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//
//@Configuration
//@EnableWebSocket
//@AllArgsConstructor
//public class WebSocketConfig implements WebSocketConfigurer {
//
//    private final WebSocketSessionManager webSocketSessionManager;
//
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(new MyWebSocketHandler(webSocketSessionManager), "/socket/{deviceId}").setAllowedOrigins("*");
//    }
//}
