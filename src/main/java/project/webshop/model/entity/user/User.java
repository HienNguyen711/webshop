package project.webshop.model.entity.user;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import project.webshop.model.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;



@Entity(name = "users")
@Getter
@Setter
@Table(name = "users")
public class User extends BaseEntity {
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    @Email
    @Size(min=3)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Size(min = 3, max = 200)
    private String name;


    @Embedded
    private Address address;


    @Column(nullable = false)
//    @Phone
    @Pattern(regexp="(^$|[0-9]{10})")
    private String phone;

    @Column
    private double balance;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
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

    public User() {
    }
}
