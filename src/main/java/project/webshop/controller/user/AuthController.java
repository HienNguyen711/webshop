package project.webshop.controller.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.*;
import project.webshop.model.dto.UserDto;
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
    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public ResponseEntity<?> addUser(@RequestBody UserDto userDto) throws Exception {
        UserDto existingUserDto;
        try {
            existingUserDto = userService.save(userDto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(existingUserDto, HttpStatus.CREATED);
    }

    @RequestMapping(value = "signin", method = RequestMethod.POST)
    public ResponseEntity<?> authenticationRequest(@RequestParam(name = "username") String username,
                                                   @RequestParam(name = "password") String password,
                                                   Device device) {
        String token = authService.createToken(username, password, device);
        if (token == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
    @RequestMapping(value = "signin/vk", method = RequestMethod.POST)
    public void VkAuthentication(Device device) {
        try {
            vkService.openAuthorizationDialog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
