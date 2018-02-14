package project.webshop.service.impl;


import lombok.NonNull;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import project.webshop.model.entity.user.PasswordResetToken;
import project.webshop.model.entity.user.User;
import project.webshop.repository.PasswordResetTokenRepository;
import project.webshop.repository.UserRepository;
import project.webshop.service.user.PasswordResetTokenService;
import project.webshop.utils.Constants;

import java.util.Date;
import java.util.UUID;

@Component
@Transactional
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {
    // autowired : PasswordResetTokenRepository

    @Autowired
    @Qualifier("passwordResetTokenRepository")
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Value("${token.expiration}")
    private Long tokenExpiration;

    @Override
    public PasswordResetToken constructPasswordResetTokenForUser(@NonNull User user) {
        String token = UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken = user.getPasswordResetToken();
        if (passwordResetToken == null) {
            passwordResetToken = new PasswordResetToken();
        }
        passwordResetToken.setExpiration(DateUtils.addMinutes(new Date(), Constant.AMOUNT_MINUTES_VALID_TOKEN));
        passwordResetToken.setToken(token);
        passwordResetToken.setUser(user);
        return passwordResetTokenRepository.save(passwordResetToken);
    }

    //ispasswordresettokenvalid
    // userId, token

    @Override
    public boolean isPasswordResetTokenValid(@NonNull Long userId,
                                             @NonNull String token) throws Exception {
        // find user
        User user = userRepository.findOne(userId);
        if (user == null || user.getPasswordResetToken() == null
                || !StringUtils.hasText(token)) {
            throw new Exception(Constants.MESSAGE_INVALID_DATA);
        }
        PasswordResetToken passwordResetToken = user.getPasswordResetToken();// get password reset token
        if (!passwordResetToken.getToken().equals(token)
                || passwordResetToken.getExpiration().before(new Date())) {
            throw new Exception(Constant.MESSAGE_NOT_VALID_TOKEN);
        }
        return true;
    }

    @Override
    public void saveNewPassword(String newPassword) throws Exception {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails == null) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_USER);
        }
        User user = userRepository.findByUsername(userDetails.getUsername());
        if (user == null) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_USER);
        }
        user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        userRepository.save(user);
    }
}
