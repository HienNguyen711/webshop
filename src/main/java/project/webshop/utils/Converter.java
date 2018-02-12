package project.webshop.utils;


import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import project.webshop.model.dto.AddressDto;
import project.webshop.model.dto.UserDto;
import project.webshop.model.entity.Address;
import project.webshop.model.entity.user.Role;
import project.webshop.model.entity.user.User;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Converter {

    // toUserDto
    public static UserDto toUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setPhone(user.getPhone());
        userDto.setName(user.getName());
        userDto.setBalance(user.getBalance());
        // Address
        Address address = user.getAddress();
        if(address != null){
            AddressDto addressDto = toAddressDto(address);
            userDto.setAddress(addressDto);
        }
        // Roles
        if (user.getRoles() != null) {
            Set<Role> roles = user.getRoles();
            Set<GrantedAuthority> collectionRoles = new HashSet<>();
            for (Role role : roles) {
                collectionRoles.add(new SimpleGrantedAuthority(role.getName()));
            }
            userDto.setRoles(collectionRoles);
        }
        return userDto;

    }
    // UserDto -> User
    public static User toUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setName(userDto.getName());
        user.setPhone(userDto.getPhone());
        user.setBalance(userDto.getBalance());
        // Address
        AddressDto addressDto = userDto.getAddress();
        if (addressDto != null) {
            Address address = toAddressEntity(addressDto);
            user.setAddress(address);
        }
        //Role
        if (userDto.getRoles() != null) {
            Set<GrantedAuthority> collection = userDto.getRoles().stream().collect(Collectors.toSet());
            Set<Role> roles = new HashSet<>();
            for (GrantedAuthority grantedAuthority : collection) {
                Role role = new Role();
                role.setName(grantedAuthority.getAuthority());
            }
            user.setRoles(roles);
        }
        return user;
    }

    // AddressDto -> Address
    public static Address toAddressEntity(AddressDto addressDto) {
        Address address = new Address();
        address.setStreet(addressDto.getStreet());
        address.setCity(addressDto.getCity());
        address.setZipcode(addressDto.getZipcode());
        address.setCountry(addressDto.getCountry());
        return address;
    }


    // toAddressDto
    public static AddressDto toAddressDto(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setStreet(address.getStreet());
        addressDto.setCity(address.getCity());
        addressDto.setZipcode(address.getZipcode());
        addressDto.setCountry(address.getCountry());
        return addressDto;
    }

}
