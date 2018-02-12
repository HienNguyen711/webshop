package project.webshop.service.user;

import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Service;


@Service
public interface AuthService {
    /**
     * CREATE TOKEN
     * @param username
     * @param password
     * @param device
     * @return String @token
     */
    String createToken(String username, String password, Device device);
}
