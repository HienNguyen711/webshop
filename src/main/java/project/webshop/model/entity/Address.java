package project.webshop.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;

@Embeddable
@Getter
@Setter
public class Address {
    @Column(nullable = false)
    private String street;


    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    @NotBlank
//    @Pattern(regexp = "[A-Za-z]\\d[A-Za-z]\\s?\\d[A-Za-z]\\d", message = "Zip code validation failed.")
    private Integer zipcode;


    @Column(nullable = false)
    private String country;

    public Address() {
    }
}
