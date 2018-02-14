package project.webshop.service.impl;


import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.webshop.model.entity.user.PasswordResetToken;
import project.webshop.model.entity.user.User;
import project.webshop.repository.UserRepository;
import project.webshop.service.MailService;
import project.webshop.service.user.PasswordResetTokenService;
import project.webshop.utils.Constants;

import java.util.Date;

@Component
@Transactional
public class MailServiceImpl implements MailService {
    // mail service : userRepo, PasswordResetToken ,JavaMailSender,
    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenService passwordResetTokenService;

    // java mail sender
    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.from}")
    private String from;

    @Value("${server.name}")
    private String serverName;

    @Value("${server.port}")
    private int serverPort;

    @Value("${server.contextPath}")
    private String serverContextPath;

    // send message
    @Override
    public void sendMessage(@NonNull Long userId,
                            @NonNull String email) throws Exception {
        // user id, email,message
        //get user
        User user = userRepository.findOne(userId);
        if (user == null
                || !user.getEmail().equals(email)) {
            throw new Exception(Constants.MESSAGE_NOT_FOUND_USER);
        }
        // construct password reset token
        PasswordResetToken passwordResetPasswordResetToken = passwordResetTokenService.constructPasswordResetTokenForUser(user);
        if (passwordResetPasswordResetToken == null || passwordResetPasswordResetToken.getToken() == null) {
            throw new Exception(Constants.MESSAGE_NOT_CONSTRUCTED_TOKEN);
        }
        //construct text message to reset pass
        String textMessageToResetPassword = constructTextMessageToResetPassword(user, passwordResetPasswordResetToken.getToken());
        // simple Mail message
        SimpleMailMessage simpleMailMessage = constructMailMessage(textMessageToResetPassword, email);
        if (simpleMailMessage == null) {
            throw new Exception(Constants.NOT_CONSTRUCTED_MESSAGE);
        }
        // send mail to user email with link to reset password with token
        mailSender.send(simpleMailMessage);
    }
    // link to reset password

    private String constructLinkToResetPassword(@NonNull User user, @NonNull String token) {
        return "http://"
                + serverName
                + ":"
                + serverPort
                + serverContextPath
                + "/users"
                + "/changePassword?id="
                + user.getId()
                + "&token="
                + token;
    }

    // construc text message to reset password
    // user token
    private String constructTextMessageToResetPassword(@NonNull User user, @NonNull String token) {
        return Constants.PASSWORD_RESET_GREETING
                + user.getUsername()
                + Constants.PASSWORD_RESET_TEXT
                + constructLinkToResetPassword(user, token)
                + Constants.PASSWORD_RESET_NOTE
                + Constants.PASSWORD_RESET_AUTHOR;
    }

    // SimpleMailMessage: constructMailMessage
    public SimpleMailMessage constructMailMessage(@NonNull String messageText,
                                                  @NonNull String email) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();// simple mail message
        // send to
        mailMessage.setTo(email);
        mailMessage.setFrom(from);
        mailMessage.setSubject(Constants.PASSWORD_RESET_SUBJECT);
        mailMessage.setSentDate(new Date());
        mailMessage.setText(messageText);
        return mailMessage;
    }
}
