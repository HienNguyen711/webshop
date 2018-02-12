package project.webshop.service.impl;


import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.webshop.model.dto.UserDto;
import project.webshop.model.entity.user.Role;
import project.webshop.model.entity.user.User;
import project.webshop.repository.RoleRepository;
import project.webshop.repository.UserRepository;
import project.webshop.service.user.UserService;
import project.webshop.utils.Constants;
import project.webshop.utils.Converter;

import java.util.HashSet;
import java.util.Set;

@Component
@Transactional
public class UserServiceImpl implements UserService{
    // autowired
    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Autowired
    @Qualifier("roleRepository")
    private RoleRepository roleRepository;

    @Override
    public UserDto save(@NonNull  UserDto userDto) throws Exception {
        // check if user is already been saved to database
            // get by user name
        if(userRepository.findByUsername(userDto.getUsername()) != null){
            throw new Exception("Username "+ userDto.getUsername() + Constants.MESSAGE_EXIST);

        }
            // get by email
        if (userRepository.findByEmail(userDto.getEmail()) != null) {
            throw new Exception("Email "
                    + userDto.getEmail()
                    + Constants.MESSAGE_EXIST
            );
        }
        // convert userDto to user to save to database
        User user = Converter.toUser(userDto);
        // userRepo.save(user)
        userRepository.save(user);
        userDto.setId(user.getId());
        // return userDTO
        return userDto;

    }

    @Override
    public UserDto findOne(Long id) throws Exception {
        User user = userRepository.findOne(id);
        if(user == null){
            return null;
        }
        return Converter.toUserDto(user);
    }

    @Override
    public UserDto update(@NonNull UserDto userDto) throws Exception {
        User user = userRepository.findOne(userDto.getId());
        if (user == null) {
            throw new Exception(Constants.MESSAGE_NOT_FOUND_USER);
        }
        user.setPhone(userDto.getPhone());
        user.setName(userDto.getName());
        user.setAddress(Converter.toAddressEntity(userDto.getAddress()));
        userRepository.save(user);
        return userDto;
    }

    @Override
    public UserDto setRoleToUser(Long userId, Long roleId) throws Exception {
        // get user by user id
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new Exception(Constants.MESSAGE_NOT_FOUND_USER);
        }
        // check if role is valid
        Role role = roleRepository.findOne(roleId);
        if (role == null) {
            throw new Exception(Constants.MESSAGE_NOT_VALID_ROLE);
        }
        Set<Role> roles = user.getRoles();
        if (roles == null) {
            roles = new HashSet<>();
        }
        // set role
        roles.add(role);
        // save to database
        user.setRoles(roles);
        // return userDto
        userRepository.save(user);
        return Converter.toUserDto(user);
    }

    @Override
    public void deleteRoleFromUser(Long userId, Long roleId) throws Exception {
        // find user by id
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new Exception(Constants.MESSAGE_NOT_FOUND_USER);
        }
        if (user.getRoles() == null) {
            throw new Exception("User has not a role!");
        }
        // get list of roles from user
        Role deletedRole = null;
        Set<Role> roles = user.getRoles();
        // check if role id = role Id , remove

        // set roles again

        // save user
        for (Role userRole : roles) {
            if (userRole.getId().equals(roleId)) {
                deletedRole = userRole;
            }
        }
        if (deletedRole == null) {
            throw new Exception("User has not this role!");
        }
        roles.remove(deletedRole);// remove deleteRole from Set<Role> roles
        user.setRoles(roles);// set roles
        userRepository.save(user);
    }
}
