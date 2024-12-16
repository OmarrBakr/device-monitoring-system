package com.example.task3.socket2;

import com.corundumstudio.socketio.SocketIOServer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ServerCommandLineRunner implements CommandLineRunner {
    private final SocketIOServer server;
    @Override
    public void run(String... args){
        try{
            server.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
    }
}