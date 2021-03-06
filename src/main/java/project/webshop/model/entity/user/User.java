package project.webshop.model.entity.user;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;
import project.webshop.model.BaseEntity;
import project.webshop.model.entity.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;



@Entity(name = "users")
@Getter
@Setter
@Table(name = "users")
public class User extends BaseEntity {
    @Column(nullable = false,unique = true)
    @NotBlank(message="Please Enter User Name!")
    private String username;

    @Column(nullable = false)
    @Email(message = "*Please provide a valid Email")
    @Size(min=3)
    private String email;

    @Column(nullable = false)
    @JsonIgnore
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;

    @Column(nullable = false)
    @Size(min = 3, max = 200)
    private String name;


    @Embedded
    private Address address;

    // Organisation 1-1
    @OneToOne(cascade = CascadeType.ALL)
    private Organisation organisation;


    @Column(nullable = false)
//    @Phone
    @Pattern(regexp="(^$|[0-9]{10})")
    private String phone;

    @Column
    private double balance;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;//many to many

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private PasswordResetToken passwordResetToken;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
    private List<Review> productReviews;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_wishProduct", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private Set<Product> wishProducts;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "user_cartProduct", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private Set<Product> cartProducts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Order> orders;

    // cart
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Promotion promotion;

    // created date
    @CreatedDate
    @Column(columnDefinition = "TIMESTAMP")
    private Date createdOn;


    // is active
    @Column(name = "isActive")
    private boolean isActive;

    public User() {
    }
}
