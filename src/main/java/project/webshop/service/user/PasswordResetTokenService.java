package project.webshop.service.user;


import org.springframework.stereotype.Service;
import project.webshop.model.entity.user.PasswordResetToken;
import project.webshop.model.entity.user.User;

@Service
public interface PasswordResetTokenService {

    PasswordResetToken constructPasswordResetTokenForUser(User user);

    // check
    boolean isPasswordResetTokenValid(Long userId, String token) throws Exception;

    // save new password
    void saveNewPassword(String newPassword) throws Exception;
}
