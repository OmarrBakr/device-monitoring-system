//package com.example.task3.services;
//
//import com.example.task3.entities.User;
//import com.example.task3.repositories.UserRepo;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@AllArgsConstructor
//public class EmailService {
//
//    private final JavaMailSender mailSender;
//    private final UserRepo userRepo;
//
//    public void sendEmail(String to, String subject, String body) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(body);
//        mailSender.send(message);
//    }
//
//    public void sendEmailsToSubscribedUsers(Long subscribedTo, String subject, String body) {
//        List<User> users = userRepo.findBySubscribedTo(subscribedTo);
//        for (User user : users) {
//            sendEmail(user.getEmail(), subject, body);
//        }
//    }
//}
