package project.webshop.model.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class UserDto {
    private Long id;

    private String username;

    private String email;

    private String password;

    private String phone;

    private String name;

    private double balance;

    private AddressDto address;

    private Collection<? extends GrantedAuthority> roles;

    public UserDto() {
    }
}
