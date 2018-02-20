package project.webshop.service.user;

import org.springframework.stereotype.Service;
import project.webshop.model.dto.UserDto;
import project.webshop.model.entity.user.User;

@Service
public interface UserService {
    // create new user
    UserDto save(UserDto userDto) throws Exception;


    // get user by id
    UserDto findOne(Long id) throws Exception;

    // update user
    UserDto update(UserDto userDto) throws Exception;

    // set role to user
    UserDto setRoleToUser(Long userId, Long roleId) throws Exception;

    // delete role from user
    void deleteRoleFromUser(Long userId,Long roleId) throws Exception;

    // get user by email
    User findByUsername(String name);
}
