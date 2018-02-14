package project.webshop.controller.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import project.webshop.model.dto.UserDto;
import project.webshop.service.MailService;
import project.webshop.service.user.PasswordResetTokenService;
import project.webshop.service.user.UserService;
import project.webshop.utils.JwtUtils;


@RestController
public class PasswordResetController {
    @Autowired
    private MailService mailService;
    @Autowired
    private PasswordResetTokenService passwordResetTokenService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtils jwtUtils;


    @RequestMapping(value = "auth/resetPassword", method = RequestMethod.POST)
    public ResponseEntity<?> sendMessage(@RequestParam Long userId,
                                         @RequestParam String email) throws Exception {
        try {
            mailService.sendMessage(userId, email);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "auth/changePassword", method = RequestMethod.GET)
    public ResponseEntity<?> changePassword(@RequestParam(name = "id") Long userId,
                                            @RequestParam(name = "token") String token,
                                            Device device) throws Exception {
        UserDto userDto;
        String authToken = null;
        try {
            if (passwordResetTokenService.isPasswordResetTokenValid(userId, token)) {
                userDto = userService.findOne(userId);
                if (userDto == null) {
                    return ResponseEntity.badRequest().build();
                }
                UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUsername());
                authToken = jwtUtils.generateToken(userDetails, device);
            }
            return new ResponseEntity<>(authToken, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "user/savePassword", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePassword(@RequestHeader(name = "Authorization") String token,
                                            @RequestParam String newPassword) throws Exception {
        try {
            passwordResetTokenService.saveNewPassword(newPassword);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().build();
    }
}
