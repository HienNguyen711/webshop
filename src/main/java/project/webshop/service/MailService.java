package project.webshop.service;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public interface MailService {
    // send message
    void sendMessage(Long userId, String email) throws Exception;


    SimpleMailMessage constructMailMessage(String messageText, String email);
}
