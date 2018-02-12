package project.webshop.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class AddressDto {

    private String street;

    private String city;

    private Integer zipcode;

    private String country;

    public AddressDto() {
    }
}