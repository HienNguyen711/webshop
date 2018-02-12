package project.webshop.controller.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.webshop.model.dto.UserDto;
import project.webshop.service.user.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // get user by user id
    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) throws Exception {
        UserDto userDto = userService.findOne(id);
        if (userDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userDto, HttpStatus.FOUND);
    }

    // update user
    @RequestMapping(value = "/user/update",method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto, @RequestHeader(name = "Authorization")String token) throws Exception{
        // update from user service
        // return response entity

            UserDto existingUserDto;
            try {
                existingUserDto = userService.update(userDto);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(existingUserDto, HttpStatus.OK);

    }




    // set role to user
    @RequestMapping(value = "admin/user/{userId}/add/role/{roleId}", method = RequestMethod.PUT)
    public ResponseEntity<?> setRoleToUser(@PathVariable("userId") Long userId,
                                           @PathVariable("roleId") Long roleId,
                                           @RequestHeader(name = "Authorization") String token) throws Exception {
        UserDto existingUserDto;
        try {
            existingUserDto = userService.setRoleToUser(userId, roleId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(existingUserDto, HttpStatus.OK);
    }




    // delete role from user
    @RequestMapping(value = "admin/user/{userId}/delete/role/{roleId}", method = RequestMethod.PUT)
    public ResponseEntity<?> deleteRoleFromUser(@PathVariable("userId") Long userId,
                                                @PathVariable("roleId") Long roleId,
                                                @RequestHeader(name = "Authorization") String token) throws Exception {
        try {
            userService.deleteRoleFromUser(userId, roleId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().build();
    }



}
