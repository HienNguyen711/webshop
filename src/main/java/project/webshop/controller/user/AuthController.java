package project.webshop.controller.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.*;
import project.webshop.service.VkService;
import project.webshop.service.user.AuthService;
import project.webshop.service.user.UserService;

@RestController
@RequestMapping("${route.authentication}")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;
    @Autowired
    private VkService vkService;

    // sign up

    // sign in

    // sign in vk


    // logout

}
